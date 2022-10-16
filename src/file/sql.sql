create table `ptgl`(
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `platform` varchar(255) DEFAULT NULL,
    `no` varchar(255) DEFAULT NULL,
    `level` varchar(255) DEFAULT NULL,
    `approve_department` varchar(255) DEFAULT NULL,
    `department` varchar(255) DEFAULT NULL,
    `principal` varchar(255) DEFAULT NULL,
    `approved_time` date DEFAULT NULL,
    `annex` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create table `tdgl`(
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `type` varchar(255) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `source` varchar(255) DEFAULT NULL,
    `no` varchar(255) DEFAULT NULL,
    `subsidize` varchar(255) DEFAULT NULL,
    `level` varchar(255) DEFAULT NULL,
    `approve_department` varchar(255) DEFAULT NULL,
    `department` varchar(255) DEFAULT NULL,
    `principal` varchar(255) DEFAULT NULL,
    `approved_time` varchar(255) DEFAULT NULL,
    `annex` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
