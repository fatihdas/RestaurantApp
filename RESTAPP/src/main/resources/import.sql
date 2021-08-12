insert into users VALUES (1,'test@mail.com','testname','testpass','ADMIN');
insert into USERS values ( 2,'testmail2@gmail.com','testname2','testpass2','USER' );

insert into menus VALUES (1,'Menu1');
insert into menus VALUES (2,'Menu2');

insert into meals VALUES (1,'patlıcan musakka + kola',35);
insert into meals VALUES (2,'meksika soslu bonfile + ayran',35);

insert into items VALUES (1,'kola',10);
insert into items VALUES (2,'elma dilim patates',10);

insert into addresses VALUES (1,'Güzelyalı',34,735);
insert into addresses VALUES (2,'Sincan',06,806);

insert into branches VALUES (1,'etiler şube','WAITING',1,1);
insert into branches VALUES (2,'ankara şube','REJECTED',2,2);

insert into restaurants VALUES (1,'Hatay Medeniyetler Sofrası',1);
insert into restaurants VALUES (2,'Baruthane pilavcısı',2);

insert into comments VALUES (1,'bla bla bla',parsedatetime('17-09-2012 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'),8,9,1);
insert into comments VALUES (2,'bla bla bla',parsedatetime('11-01-2016 05:30:22.21', 'dd-MM-yyyy hh:mm:ss.SS'),2,6,1);

insert into item_meal values (1,1);
insert into item_meal values (1,2);

insert into meal_menu values ( 1,1 );
insert into meal_menu values ( 2,1 );

insert into restaurants_branch_list values ( 1,1 );
insert into restaurants_branch_list values ( 1,2 );



