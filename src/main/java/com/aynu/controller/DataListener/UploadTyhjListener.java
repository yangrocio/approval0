package com.aynu.controller.DataListener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.aynu.bean.Hylw;
import com.aynu.bean.Tyhj;
import com.aynu.dao.HylwDao;
import com.aynu.dao.TyhjDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板的读取类
 *
 * @author Jiaju Zhuang
 */
// 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
@Slf4j
@Controller
public class UploadTyhjListener extends AnalysisEventListener<Tyhj> {
//    private static final Logger LOGGER =
//            LoggerFactory.getLogger(com.alibaba.easyexcel.test.demo.read.DemoDataListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 500;
    List<Tyhj> list = new ArrayList<Tyhj>();
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    private TyhjDao tyhjDao;

//    public UploadDataListener() {
//        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
//        hylwDao = new HylwDao();
//    }

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     */
    public UploadTyhjListener(TyhjDao tyhjDao) {
        this.tyhjDao = tyhjDao;
    }

    /**
     * 这个每一条数据解析都会来调用
     * @param context
     */
    @Override
    public void invoke(Tyhj data, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
        tyhjDao.insertExcel(list);
        log.info("存储数据库成功！");
    }
}