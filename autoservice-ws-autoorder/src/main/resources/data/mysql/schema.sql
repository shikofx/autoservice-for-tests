DROP TABLE IF EXISTS auto_order;
--
CREATE TABLE auto_order(
  order_id int(20) unsigned NOT NULL auto_increment,
  order_date varchar(50) NOT NULL,
  owner_name varchar(50) NOT NULL,
  PRIMARY KEY(order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;