INSERT INTO `user_login`.`book_info_be`(`bi_title`,`bi_desc`,`bi_publish_year`)VALUES('індыйская дзяўчынка','Дзяўчына, якая правяла сваё жыццё на жыццё на гары',1990);
INSERT INTO `user_login`.`book_info_be`(`bi_title`,`bi_desc`,`bi_publish_year`)VALUES('ява асноўнай','даведацца асновы ява з нуля',1995);

insert into user_login.book_info_en(`bi_title`,`bi_desc`,`bi_publish_year`) values('indian girl', 'The girl who spent her life living on the mountain', 1990);
insert into user_login.book_info_en(`bi_title`,`bi_desc`,`bi_publish_year`) values('java basic', 'learbook_author_ben java basics from scratch', 1995);

INSERT INTO `user_login`.`book_author_en` (`ba_name`) VALUES ('Ram Singh');
INSERT INTO `user_login`.`book_author_en` (`ba_name`) VALUES ('Grey Hegh');

INSERT INTO `user_login`.`book_author_be` (`ba_name`) VALUES ('рам Сінгх');
INSERT INTO `user_login`.`book_author_be` (`ba_name`) VALUES ('грэй Хе');

INSERT INTO `user_login`.`book_type_be` (`bt_name`) VALUES ('папера звязаны');
INSERT INTO `user_login`.`book_type_be` (`bt_name`) VALUES ('электронная кніга');

INSERT INTO `user_login`.`book_type_en` (`bt_name`) VALUES ('paperBound');
INSERT INTO `user_login`.`book_type_en` (`bt_name`) VALUES ('e-book');

INSERT INTO `user_login`.`ul_lang` (`l_id`, `l_type`, `l_country`) VALUES ('1', 'hi', 'IN');
INSERT INTO `user_login`.`ul_lang` (`l_id`, `l_type`, `l_country`) VALUES ('2', 'en', 'US');
INSERT INTO `user_login`.`ul_lang` (`l_id`, `l_type`, `l_country`) VALUES ('3', 'ko', 'KR');
INSERT INTO `user_login`.`ul_lang` (`l_id`, `l_type`, `l_country`) VALUES ('4', 'be', 'BY');

INSERT INTO `user_login`.`book_info_author_be` (`bi_id`, `ba_id`) VALUES ('1', '1');
INSERT INTO `user_login`.`book_info_author_be` (`bi_id`, `ba_id`) VALUES ('2', '1');
INSERT INTO `user_login`.`book_info_author_be` (`bi_id`, `ba_id`) VALUES ('2', '2');

INSERT INTO `user_login`.`book_info_author_en` (`bi_id`, `ba_id`) VALUES ('1', '1');
INSERT INTO `user_login`.`book_info_author_en` (`bi_id`, `ba_id`) VALUES ('2', '1');
INSERT INTO `user_login`.`book_info_author_en` (`bi_id`, `ba_id`) VALUES ('2', '2');

INSERT INTO `user_login`.`book_info_lang_be` (`bi_id`, `l_id`) VALUES ('1', '2');
INSERT INTO `user_login`.`book_info_lang_be` (`bi_id`, `l_id`) VALUES ('1', '4');
INSERT INTO `user_login`.`book_info_lang_be` (`bi_id`, `l_id`) VALUES ('2', '4');

INSERT INTO `user_login`.`book_info_lang_en` (`bi_id`, `l_id`) VALUES ('1', '2');
INSERT INTO `user_login`.`book_info_lang_en` (`bi_id`, `l_id`) VALUES ('1', '4');
INSERT INTO `user_login`.`book_info_lang_en` (`bi_id`, `l_id`) VALUES ('2', '4');

INSERT INTO `user_login`.`book_info_type_be` (`bi_id`, `bt_id`) VALUES ('1', '1');
INSERT INTO `user_login`.`book_info_type_be` (`bi_id`, `bt_id`) VALUES ('1', '2');
INSERT INTO `user_login`.`book_info_type_be` (`bi_id`, `bt_id`) VALUES ('2', '2');

INSERT INTO `user_login`.`book_info_type_en` (`bi_id`, `bt_id`) VALUES ('1', '1');
INSERT INTO `user_login`.`book_info_type_en` (`bi_id`, `bt_id`) VALUES ('1', '2');
INSERT INTO `user_login`.`book_info_type_en` (`bi_id`, `bt_id`) VALUES ('2', '2');
