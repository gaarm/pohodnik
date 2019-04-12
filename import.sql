CREATE TABLE `members` (
`id` int( 11 ) NOT NULL AUTO_INCREMENT ,
`username` varchar( 30 ) NOT NULL ,
`email` varchar( 50 ) NOT NULL ,
`password` varchar( 128 ) NOT NULL ,
PRIMARY KEY ( `id` ) ,
UNIQUE KEY `username` ( `username` )
) ENGINE = MYISAM DEFAULT CHARSET = utf8;

INSERT INTO `members`(`username`, `email`, `password`) VALUES ('gasper','gasper@test.com','pass1');
INSERT INTO `members`(`username`, `email`, `password`) VALUES ('joze','joze@test.com','pass2');
INSERT INTO `members`(`username`, `email`, `password`) VALUES ('miha','miha@test.com','pass3');
