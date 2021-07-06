CREATE DATABASE IF NOT EXISTS UNDERWATER;

DROP TABLE IF EXISTS `News`;
DROP TABLE IF EXISTS `Manager`;
DROP TABLE IF EXISTS `QA`;

DROP TABLE IF EXISTS `ForumComment`;
DROP TABLE IF EXISTS `ArticleReport`;
DROP TABLE IF EXISTS `ForumRate`;
DROP TABLE IF EXISTS `ForumArticle`;
DROP TABLE IF EXISTS `ArticleTitleOpt`;

DROP TABLE IF EXISTS `AdPic`;
DROP TABLE IF EXISTS `AdOrder`;
DROP TABLE IF EXISTS `AdMember`;

DROP TABLE IF EXISTS `Chat`;
DROP TABLE IF EXISTS `Follow`;
DROP TABLE IF EXISTS `CustomerReply`;

DROP TABLE IF EXISTS `ProductPhoto`;
DROP TABLE IF EXISTS `ShoppingCar`;
DROP TABLE IF EXISTS `OrderList`;
DROP TABLE IF EXISTS `OrderForProduct`;
DROP TABLE IF EXISTS `Product`;

DROP TABLE IF EXISTS `MemberRate`;
DROP TABLE IF EXISTS `PartyMember`;
DROP TABLE IF EXISTS `Party`;

DROP TABLE IF EXISTS `LocationRate`;
DROP TABLE IF EXISTS `Collections`;
DROP TABLE IF EXISTS `OderForGroup`;
DROP TABLE IF EXISTS `GroupTour`;

DROP TABLE IF EXISTS `Diveinfo`;
DROP TABLE IF EXISTS `Member`;


CREATE TABLE `Member` (
    `userID` int NOT NULL AUTO_INCREMENT COMMENT '會員編號',
  `account` varchar(50) NOT NULL COMMENT '帳號',
  `pwd` varchar(20) NOT NULL COMMENT '密碼',
  `nickName` varchar(30) NOT NULL COMMENT '暱稱',
  `userName` varchar(50) NOT NULL COMMENT '姓名',
  `gender` varchar(1) DEFAULT NULL COMMENT '性別',
  `birthDate` date DEFAULT NULL COMMENT '會員生日',
  `phone` varchar(10) DEFAULT NULL COMMENT '聯絡電話',
  `certification` char(2) DEFAULT NULL COMMENT '證照',
  `certificationPic` blob COMMENT '證照圖片',
  `personID` char(10) DEFAULT NULL COMMENT '身份證字號',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
-- 註解是原本的，下一行是因為要在DDL塞假資料改動的，到時候如果有問題請注意這邊y
  -- `createTime` timestamp NOT NULL COMMENT '帳號建立時間',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '帳號建立時間',
  `status` int NOT NULL DEFAULT '2' COMMENT '帳號狀態',
-- 註解是原本的，下一行是因為要在DDL塞假資料改動的，到時候如果有問題請注意這邊
  -- `upDateTime` timestamp NOT NULL COMMENT '帳號更新時間',
  `upDateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '帳號更新時間',
  `ratePeople` int NOT NULL DEFAULT '0' COMMENT '被評價總人數',
  `ratePoint` int NOT NULL DEFAULT '0' COMMENT '被評價總分',
  `personPhoto` blob COMMENT '個人照片',
  PRIMARY KEY (`userID`),
  UNIQUE KEY `UK_MEMBER_account` (`account`)
) COMMENT='會員';
insert into Member (account, pwd, nickName,userName,address, status, ratePeople, ratePoint) 
value("PeterWu", "123456", "Tomcat代言人", "吳永志", "104台北市中山區南京東路三段219號5樓", 1, 999, 9999 );
insert into Member (account, pwd, nickName,userName, address, status, ratePeople, ratePoint) 
value("DavidWu", "123456", "Java代言人", "吳冠宏", "104台北市中山區南京東路三段219號5樓", 1, 999, 9999 );
insert into Member (account, pwd, nickName,userName, address, status, ratePeople, ratePoint) 
value("TomcatWu", "123456", "外號超多湯姆貓本貓來了", "湯姆貓","104台北市中山區南京東路三段219號5樓", 1, 99, 999 );
insert into Member (account, pwd, nickName,userName, address, status, ratePeople, ratePoint) 
value("JSKiller", "123456", "JS殺手", "許尚媛", "104台北市中山區南京東路三段219號5樓", 2, 99, 999 );
insert into Member (account, pwd, nickName,userName, address, status, ratePeople, ratePoint) 
value("JustAskMe", "123456", "問我就對了", "李偉銘", "104台北市中山區南京東路三段219號5樓", 1, 999, 9999 );

CREATE TABLE `Diveinfo` (
  `pointSN` int NOT NULL AUTO_INCREMENT COMMENT '潛點編號',
  `pointName` varchar(20)  NOT NULL,
  `latitude` double NOT NULL COMMENT '緯度',
  `longitude` double NOT NULL COMMENT '經度',
  `view` varchar(20) DEFAULT "",
  `introduction` longtext NOT NULL COMMENT '潛點圖文',
  `season` varchar(20) NOT NULL COMMENT '季節',
  `local` varchar(20) DEFAULT "",
  `pic` longblob COMMENT '預覽圖',
  `ratePoint` int NOT NULL COMMENT '評價總分' DEFAULT 0,
  `ratePeople` int NOT NULL COMMENT '評價人數' DEFAULT 0,
  `status` varchar(20) NOT NULL DEFAULT 0,
  PRIMARY KEY (`pointSN`)
) COMMENT='潛點資訊' AUTO_INCREMENT = 200001;

insert into Diveinfo(pointName,latitude,longitude,`view`,introduction,season,
`local`,pic,ratePoint,ratePeople,status)
values
("澎湖仙島",23.249750, 119.674783,"蚵仔嫂的故鄉",
"澎湖南方四島國家公園海域遊憩區擁有美麗壯闊的珊瑚生態和魚群，歡迎民眾來親近海洋，雖然目前交通仍然不方便，但也因此保留了更多原始風貌",
"春夏秋冬","離島",null,5,1,"上架"),
("墾丁獨立礁",21.563336, 120.45536,"海蛞蝓與豆丁海馬朝聖地",
"獨立礁是墾丁船潛最受歡迎，也是潛水員們最喜歡的潛點。獨立礁是一顆矗立在空曠沙地上的巨大礁石。狀似龍頭的礁岩面南而立，磐頂約8米，置底30米。這個潛點最受歡迎的明星生物，是棲息於24米海扇上的豆丁海馬。迷你而圓滾滾的迷人身形是潛水員們不能錯過的探訪對象，真的是太可愛了 !",
"春夏秋冬","南部",null,5,1,"上架"),
("墾丁合界沈船",21.959135, 120.710401,"行駛在海底的船",
"合界，建議船潛，如果要岸潛的話，可以在水面移動到位置再下潛。必來打卡的點是這裡水下32米只剩下船骨的沉船，因為長得很像肋骨，大家都稱之為「排骨」！下水點的兩邊都有公車站牌很好找～這裡最深有大約有34米，加上有時水流湍急、浪大，適合中高階的大家來參觀！船骨成了魚礁，也有豐富的生態可以觀看。",
"春夏秋冬","南部",null,5,1,"上架"),
("小琉球美人洞",22.353668, 120.37315,"海龜集會所",
"堪稱海龜數量最多的潛點，下水時要注意溝槽地形，時而有浪。有時水質清澈，能見度可達20米以上。",
"春夏秋冬","離島",null,5,1,"上架"),
("東北角龍洞",25.112701, 121.919074,"龍洞1號",
"位在龍洞灣公園內，有大型室內停車場，停車及著裝方便，亦有潛水步道，是東北角潛水最輕鬆的潛點。",
"春夏秋冬","北部",null,5,1,"上架"),
("綠島石朗",22.65577,121.47454,"浮潛與水肺都可以的地方",
"綠島是國際級的潛水天堂，而位於西岸沿海一帶的石朗海域，則是綠島最受歡迎的潛水勝地，和柴口、大白沙並列為綠島三大潛水區。這裡靠近島內商家雲集的南寮村，浮潛後徒步走去用餐只要十分鐘，非常方便；南寮漁港也在不遠處，飽餐一頓再搭船出海賞鯨，多麼悠閒愜意！",
"春夏秋冬","離島",null,5,1,"上架");

-- --------------------------------------套裝行程----------------------------------------


create table `GroupTour` (
	`groupTourSN` int NOT NULL AUTO_INCREMENT COMMENT '套裝行程編號',
    `tourName` varchar(30) not null comment '行程名稱',
    `tourPic` longblob comment '行程圖片',
    `startTime` date not null comment '行程起始日期',
    `endTime` date not null comment '行程結束日期',
    `regTime` date not null comment '報名開始日期',
    `closeTime` date not null comment '報名結束日期',
    `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '行程建立日期',
    `pointSN` int not null comment '潛點編號',
    `price` int not null comment '行程價格',
    `attendNumber` int not null DEFAULT '0' comment '參加人數',
    `limitNumder` int not null comment '限制人數',
    `certificationLimit` char(2) comment '資格限制',
    `status` char(1) not null comment '出團狀態',
    `content` longtext not null comment '行程圖文',
	CONSTRAINT GroupTour_pontSN_FK FOREIGN KEY (pointSN) REFERENCES DiveInfo (pointSN),
	PRIMARY KEY (`groupTourSN`)
) COMMENT='套裝行程' AUTO_INCREMENT=6001;

INSERT INTO GroupTour (tourName, startTime, endTime, regTime, closeTime, pointSN, price, attendNumber, limitNumder, certificationLimit, `status`, content) VALUES ('墾丁獨立礁二日遊', '2021-07-09', '2021-07-10', '2021-06-01', '2021-07-02', 200001, 12000, 0, 10, '1', '1', 'content');


create table `Collections` (
	`userID` int not null comment '會員編號',
    `groupTourSN` int not null comment '套裝行程編號',
    CONSTRAINT Collections_userID_FK FOREIGN KEY (userID) REFERENCES Member (userID),
    CONSTRAINT Collections_groupTourSN_FK FOREIGN KEY (groupTourSN) REFERENCES GroupTour (groupTourSN)
) COMMENT='套裝行程收藏';

INSERT INTO Collections (userID, groupTourSN) VALUES (1, 6001);


create table `OderForGroup` (
	`orderSN` int NOT NULL AUTO_INCREMENT COMMENT '套裝行程訂單編號',
	`userID` int not null comment '會員編號',
    `groupTourSN` int not null comment '套裝行程編號',
    `totalPrice` int not null comment '訂單總額',
	`purchaseDate`  date not null comment '購買日期',
    `phone` varchar(20) not null comment '手機',
    `personID` char(10) not null comment '身分證號',
    `birthdate` date not null comment '會員生日',
     CONSTRAINT OderForGroup_userID_FK FOREIGN KEY (userID) REFERENCES Member (userID),
     CONSTRAINT OderForGroup_groupTourSN_FK FOREIGN KEY (groupTourSN) REFERENCES GroupTour (groupTourSN),
	  PRIMARY KEY (`orderSN`)
) COMMENT='套裝行程訂單' AUTO_INCREMENT=6001;

INSERT INTO OderForGroup (userID, groupTourSN, totalPrice, purchaseDate, phone, personID, birthdate) VALUES (1, 6001, 10000, '2021-06-01', '0912345678', 'A123456789', '1996-06-19');


create table `LocationRate` (
	`SN` int NOT NULL AUTO_INCREMENT COMMENT '潛點評價編號',
    `pointSN` int not null comment '潛點編號',
	`userID` int not null comment '評價方',
    `rate` int comment '評價',
    `rateDetail` varchar(1000) comment '評價詳細內容',
    `createTime` timestamp not null DEFAULT CURRENT_TIMESTAMP comment '評價時間',
	CONSTRAINT LocationRate_pontSN_FK FOREIGN KEY (pointSN) REFERENCES DiveInfo (pointSN),
	CONSTRAINT LocationRate_userID_FK FOREIGN KEY (userID) REFERENCES Member (userID),
	  PRIMARY KEY (`SN`)
) COMMENT='地點評價資料' AUTO_INCREMENT=6001;

INSERT INTO LocationRate (pointSN, userID, rate, rateDetail) VALUES (200001, 1, 5, '超級好玩!中性浮力練好再去比較適合喔。');


-- --------------------------------------揪團&會員評價----------------------------------------

CREATE TABLE `Party` (
  `partySN` int NOT NULL AUTO_INCREMENT COMMENT '揪團編號',
  `partyHost` int NOT NULL COMMENT '主揪會員編號',
  `partyTitle` varchar(100) NOT NULL COMMENT '揪團主旨',
  `regDate` date NOT NULL COMMENT '報名開始時間',
  `closeDate` date NOT NULL COMMENT '報名結束時間',
  `startDate` date NOT NULL COMMENT '此團開始時間',
  `endDate` date NOT NULL COMMENT '此團結束時間',
  `partyMinSize` int NOT NULL COMMENT '揪團最低人數',
  `partyLocation` int NOT NULL COMMENT '揪團地點',
  `partyDetail` longtext NOT NULL COMMENT '揪團詳細內容',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '揪團發文時間',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '揪團狀態 0:熱烈報名中, 1:已額滿, 2:已結束, 3: 已取消, 4: 已成團(仍可收), 5: 被下架',
  PRIMARY KEY (`partySN`),
  KEY `FK_Party_partyHost` (`partyHost`),
  KEY `FK_Party_partyLocation` (`partyLocation`),
  CONSTRAINT `FK_Party_partyHost` FOREIGN KEY (`partyHost`) REFERENCES `Member` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Party_partyLocation` FOREIGN KEY (`partyLocation`) REFERENCES `DiveInfo` (`pointSN`) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT='揪團列表' AUTO_INCREMENT=400001;

insert into party (partyHost, partyTitle, regDate, closeDate, startDate, endDate, partyMinSize, partyLocation, partyDetail)
values 
('1', '要不要一起去找珊迪', '2021-01-01', '2021-01-31', '2021-03-03', '2021-03-04', '2', '200001', '很久沒有去找珊迪玩耍了! 大家有沒有興趣來約一下啊!'), 
('1', '之前是不是也說要去追逐派大星', '2021-09-09', '2021-10-10', '2021-12-20', '2021-12-23', '3', '200002', '不過派大星可能在冬眠惹...'),
('2', '有人想去很酷的外星海嗎?!', '2021-06-18', '2021-09-18', '2021-12-31', '2022-01-01', '10', '200003', '聽說克卜勒22b也有海洋，要不要一起去玩玩看呀?'),
('3', '開一團教你如何宅在家裡也能享受潛水樂趣', '2021-05-05', '2021-07-05', '2021-07-06', '2021-07-08', '2', '200004', '這可厲害了沒聽說過吧!');

CREATE TABLE `PartyMember` (
  `partyMemberSN` int NOT NULL AUTO_INCREMENT COMMENT '揪團團員名單流水號',
  `partySN` int NOT NULL COMMENT '揪團編號',
  `partyMember` int NOT NULL COMMENT '團員會員編號',
  `gender` varchar(1) NOT NULL COMMENT '性別 0:男 1:女',
  `email` varchar(50) NOT NULL COMMENT 'Email',
  `phone` varchar(10) NOT NULL COMMENT '手機',
  `birthDate` date COMMENT '生日',
  `personID` char(10) COMMENT '身份證字號',
  `certification` char(1) COMMENT '證照',
  `certificationPic` longblob COMMENT '證照圖片',
  `appliedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '報名時間',
  `comment` varchar(1000) DEFAULT NULL COMMENT '備註',
  `status` int NOT NULL DEFAULT '0' COMMENT '報名狀態 0:待審核 1:審核通過 2:審核未通過 3:不顯示',
  PRIMARY KEY (`partyMemberSN`),
  UNIQUE KEY `UK_PartyMember_partySN_partyMember` (`partySN`,`partyMember`),
  KEY `FK_PartyMember_partyMember` (`partyMember`),
  CONSTRAINT `FK_PartyMember_partyMember` FOREIGN KEY (`partyMember`) REFERENCES `Member` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_PartyMember_partySN` FOREIGN KEY (`partySN`) REFERENCES `Party` (`partySN`) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT='揪團團員名單' AUTO_INCREMENT=400001;

insert into PartyMember (partySN, partyMember, gender, email, phone, birthDate, personID, comment)
values 
('400001', '2', '1', 'KingOfJava@tibame.com', '0988888888', '1998-02-02', 'B888888888', '大吳老師我也想參加'), 
('400001', '3', '2', 'IAmTomCat@tibame.com', '0977777777', '1900-01-01', 'C777777777', '大吳老師帶我走'),
('400003', '4', '2', 'JSPrincessIsMe@tibame.com', '0966666666', '1995-07-04', 'B666666666', '現在是什麼情況?只有我參加嗎?'),
('400003', '5', '1', 'MySQLConqueror@tibame.com', '0955555555', '1990-11-12', 'B555555555', '還有我, 我也想跟小吳老師一起去玩!');

CREATE TABLE `MemberRate` (
  `SN` int NOT NULL AUTO_INCREMENT COMMENT '會員評價流水編號',
  `partySN` int DEFAULT NULL COMMENT '揪團編號',
  `orderSN` int DEFAULT NULL COMMENT '套裝行程訂單編號',
  `rateMaker` int NOT NULL COMMENT '評論方',
  `rateRecipiant` int NOT NULL COMMENT '被評論方',
  `rate` int NOT NULL COMMENT '評價',
  `rateDetail` varchar(3000) DEFAULT NULL COMMENT '評價詳細內容',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '評價時間',
  PRIMARY KEY (`SN`),
  KEY `FK_MemberRate_partySN` (`partySN`),
  KEY `FK_MemberRate_orderSN` (`orderSN`),
  KEY `FK_rateMaker` (`rateMaker`),
  KEY `FK_rateRecipiant` (`rateRecipiant`),
  CONSTRAINT `FK_MemberRate_orderSN` FOREIGN KEY (`orderSN`) REFERENCES `GroupTour` (`groupTourSN`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_MemberRate_partySN` FOREIGN KEY (`partySN`) REFERENCES `Party` (`partySN`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_rateMaker` FOREIGN KEY (`rateMaker`) REFERENCES `Member` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_rateRecipiant` FOREIGN KEY (`rateRecipiant`) REFERENCES `Member` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  UNIQUE KEY `UK_MemberRate_partySN_rateMaker_rateRecipiant` (`partySN`,`rateMaker`, `rateRecipiant`),
  UNIQUE KEY `UK_MemberRate_orderSN_rateMaker_rateRecipiant` (`orderSN`,`rateMaker`, `rateRecipiant`)
) COMMENT='會員評價';

insert into MemberRate (partySN, rateMaker, rateRecipiant, rate, rateDetail)
values 
('400001', '1', '3', '5', '雖然臭貓常常死掉但還是要給你很高分!要乖乖的喔!'),
('400001', '2', '3', '1', '請向九命怪貓多多看齊好嗎'),
('400001', '1', '2', '5', '冠宏老師一定都有教過，你們有沒有聽我就不知道了^_^'),
('400003', '5', '4', '3', '上元你是不是報錯揪團了??'),
('400003', '2', '4', '3', '我也有同感(點頭)'),
('400003', '4', '2', '4', '有可能喔~但是我覺得很好玩啊!!!');

-- --------------------------------------商城相關表格----------------------------------------

CREATE TABLE `Product` (
  `productSN` int NOT NULL AUTO_INCREMENT COMMENT '商品編號',
  `productClass` varchar(20) NOT NULL COMMENT '商品類別',
  `productName` varchar(80) NOT NULL COMMENT '商品名稱',
  `productPrice` int NOT NULL COMMENT '商品單價',
  `productQuantity` int NOT NULL COMMENT '商品數量',
  `productStatus` varchar(20) NOT NULL COMMENT '商品狀態',
  `productPhoto` longblob COMMENT '商品預覽圖',
  `productDetail` longtext NOT NULL COMMENT '商品說明',
  `productCreateTime` date NOT NULL COMMENT '上架時間',
  `productDiscount` varchar(20) NOT NULL COMMENT '優惠品',
  `productPrime` varchar(20) NOT NULL COMMENT '精選品',
  `ratingPoint` int NOT NULL DEFAULT 0 COMMENT '評價總分',
  `ratingNumber` int NOT NULL DEFAULT 0 COMMENT '評價人數',
  PRIMARY KEY (`productSN`)
) COMMENT='商品';

INSERT INTO Product (productClass,productName,productPrice,productQuantity,productStatus,
productPhoto,productDetail,productCreateTime,productDiscount,productPrime,ratingPoint,ratingNumber)
values
("蛙鞋","穿脫超省力power牌鞋鞋",799,60,"上架",null,
"話不要那麼多啦，趕快買，買到賺到。","2021-06-14","否","是",22,3),
("呼吸管","大章魚浮潛三寶呼吸管",999,50,"上架",null,
"吸入後，空氣直接從兩側進入，不會停留在視野內。
呼氣時，排氣會從中間的排氣門排出，以防止產生霧氣。","2021-06-15","是","是",15,2),
("面鏡","超值防霧潛水面鏡",699,70,"上架",null,
"強化防霧耐磨鏡片，超高清晰度。
3D建模製造，符合亞洲人臉型，舒適防滲水。
一體式鏡片，無中柱遮擋，視野擴增至180度。
日本進口高級食品級矽膠材質，壓力實驗耐30萬次拉扯。
呼吸管排水閥設計，不易進水輕鬆悠遊。","2021-05-05","否","否",21,3),
("防寒曬衣物","TRUDIVE 防寒衣【Siren 海妖系列】半身滑面",3300,40,"上架",null,
"針對亞洲女性身材調整版型，展現出每位女性的自信。Siren不僅帶給妳水下的舒適度，更期待妳成為海中那位迷人的海妖。
防寒衣採用YAMATO 或 JAKO MSL 等高彈性的氯丁橡膠製作，內層採用高彈性布料，外層則以TRUDIVE 的專利鈦塗層專利技術上色。
鈦塗層滑面加上超彈內裡布，不僅好穿，拍照也有型。","2021-05-09","否","是",15,2),
("氣瓶","DEEP Pro迷你SCUBA潛水罐",3800,10,"上架",null,
"品牌：Catalina
尺寸：S80
鋁合金材質
含SHERWOOD DIN/YOKE 兩用氣瓶閥、氣瓶底座
填充容積:80CUFT/11.1L
工作壓力:200BAR/3000PSI
氣瓶直徑:18.5CM
氣瓶高度:66CM
氣瓶重量:15公斤","2021-06-15","是","是",6,1),
("照明燈","Deep Pro 潛伴信號燈",990,25,"上架",null,
"• 亮度：108流明
• 電池：1個AA電池，可更換
• 發光角度：15度
• 尺寸：86mm x 20mm x 20mm
• 重量：68g  /  42.8g ( in sea )
• 防水：100M
• 顏色：紅 / 粉 / 藍 / 黑","2021-06-19","否","否",0,0),
("其他配件","Aropec SUR-H106 浮力袋(口吹+洩氣閥+灌氣+外袋)",1650,30,"上架",null,
"可用口吹、BC管、或二級頭底部開口進充氣
附掛勾可簡易掛在BC上
含洩氣閥，過充可自動洩氣
底部附黏扣帶, 未充氣時方便收納
附含掛勾之收納袋
尺寸: 17.8 x 122cm (7 x 48吋)
附吹嘴及安全洩壓閥","2021-06-22","是","是",0,0);


CREATE TABLE `OrderForProduct` (
  `orderSN` int NOT NULL AUTO_INCREMENT COMMENT '訂單編號',
  `userID` int NOT NULL COMMENT '會員編號',
  `purchaseDate` timestamp NOT NULL DEFAULT NOW() COMMENT '購買時間',
  `totalPrice` int NOT NULL COMMENT '結帳總金額',
  `orderStatus` varchar(20) NOT NULL COMMENT '訂單狀態',
  PRIMARY KEY (`orderSN`),
  KEY `FK_OrderForProduct_userID` (`userID`),
  CONSTRAINT `FK_OrderForProduct_userID` FOREIGN KEY (`userID`) REFERENCES `Member` (`userID`)
) COMMENT='商品訂單';

INSERT INTO OrderForProduct (userID,totalPrice,orderStatus)
values
(1,1798,"處理中"),
(2,4499,"已完成"),
(3,5797,"已完成"),
(4,11997,"處理中"),
(5,3196,"處理中");


CREATE TABLE `OrderList` (
  `orderListSN` int NOT NULL AUTO_INCREMENT COMMENT '明細流水號',
  `productSN` int NOT NULL COMMENT '商品編號',
  `orderSN` int NOT NULL COMMENT '訂單編號',
  `purchaseQuantity` int NOT NULL COMMENT '購買數量',
  `productPrice` int NOT NULL COMMENT '商品單價',
  `rating` int NOT NULL COMMENT '商品評價',
  PRIMARY KEY (`orderListSN`),
  KEY `FK_OrderList_productSN` (`productSN`),
  KEY `FK_OrderList_OrderSN` (`orderSN`),
  CONSTRAINT `FK_OrderList_OrderSN` FOREIGN KEY (`orderSN`) REFERENCES `OrderForProduct` (`orderSN`),
  CONSTRAINT `FK_OrderList_productSN` FOREIGN KEY (`productSN`) REFERENCES `Product` (`productSN`)
) COMMENT='商品訂單明細';

INSERT INTO OrderList (productSN,orderSN,purchaseQuantity,productPrice,rating)
values
(1,1,1,799,8),
(2,1,1,999,7),
(3,2,1,699,7),
(5,2,1,3800,6),
(1,3,1,799,8),
(2,3,1,999,8),
(3,3,1,699,7),
(4,3,1,3300,6),
(3,4,3,699,7),
(4,4,3,3300,9),
(1,5,4,799,6);


CREATE TABLE `ShoppingCar` (
  `shoppingCarSN` int NOT NULL AUTO_INCREMENT COMMENT '購物車編號',
  `userID` int NOT NULL COMMENT '會員編號',
  `productSN` int NOT NULL COMMENT '商品編號',
  `purchaseQuantity` int NOT NULL COMMENT '購買數量',
  `productPrice` int NOT NULL COMMENT '商品單價',
  `totalPrice` int NOT NULL COMMENT '結帳總金額',
  PRIMARY KEY (`shoppingCarSN`),
  KEY `FK_ShoppingCar_userID` (`userID`),
  KEY `FK_ShoppingCar_productSN` (`productSN`),
  CONSTRAINT `FK_ShoppingCar_productSN` FOREIGN KEY (`productSN`) REFERENCES `Product` (`productSN`),
  CONSTRAINT `FK_ShoppingCar_userID` FOREIGN KEY (`userID`) REFERENCES `Member` (`userID`)
) COMMENT='購物車';

INSERT INTO ShoppingCar (userID,productSN,purchaseQuantity,productPrice,totalPrice)
values
(1,2,2,999,1998),
(1,3,2,699,1398),
(1,4,2,3300,6600),
(2,5,1,3800,3800),
(3,2,1,999,999),
(3,4,1,3300,3300),
(4,1,6,799,4794);


-- --------------------------------------會員相關表格 CustomerReply Follow Chat----------------------------------------


-- create table `CustomerReply` (     -- USE REDIS, NOT MYSQL
-- 	`customerReplySN` int not null AUTO_INCREMENT comment '訊息回覆編號',
-- 	`userID` int not null comment '會員編號',
--    `type` char(1) not null comment '類型',
--    `content` varchar(500) not null comment '回應內容',
--    `sendTime` timestamp not null comment '訊息傳送時間',
--     CONSTRAINT CustomerReply_userID_FK FOREIGN KEY (userID) REFERENCES Member (userID),
-- 	  PRIMARY KEY (`customerReplySN`)
-- ) COMMENT='即時客服回應' AUTO_INCREMENT=60001;


CREATE TABLE `Follow` (
  `follower` int NOT NULL COMMENT '追蹤者編號',
  `followed` int NOT NULL COMMENT '被追蹤者編號',
  PRIMARY KEY (`follower`,`followed`),
  KEY `FK_Follow_followed` (`followed`),
  CONSTRAINT `FK_Follow_followed` FOREIGN KEY (`followed`) REFERENCES `member` (`userID`),
  CONSTRAINT `FK_Follow_follower` FOREIGN KEY (`follower`) REFERENCES `member` (`userID`)
) COMMENT='追蹤';

insert into Follow(follower,followed) values (1,2);
insert into Follow(follower,followed) values (2,1);


CREATE TABLE `Chat` (
  `chatSN` int NOT NULL AUTO_INCREMENT COMMENT '聊天紀錄編號',
  `fromAccount` int NOT NULL COMMENT ' 發送編號',
  `toAccount` int NOT NULL COMMENT '接受編號',
  `content` varchar(256) NOT NULL COMMENT '內容',
  `dateTime` timestamp NOT NULL COMMENT '時間',
  PRIMARY KEY (`chatSN`),
  KEY `FK_Chat_fromAccount` (`fromAccount`),
  KEY `FK_Chat_toAccount` (`toAccount`),
  CONSTRAINT `FK_Chat_fromAccount` FOREIGN KEY (`fromAccount`) REFERENCES `member` (`userID`),
  CONSTRAINT `FK_Chat_toAccount` FOREIGN KEY (`toAccount`) REFERENCES `member` (`userID`)
) COMMENT='聊天紀錄' AUTO_INCREMENT=900001;

insert into Chat(fromAccount, toAccount,content,dateTime)
values (1,2,"啟源吃早餐",now());
insert into Chat(fromAccount, toAccount,content,dateTime)
values (2,1,"喔市喔",now()+INTERVAL 5 SECOND);

-- --------------------------------------AD----------------------------------------


CREATE TABLE `AdMember` (
  `adUserID` int NOT NULL AUTO_INCREMENT COMMENT '會員流水編號',
  `account` varchar(50) NOT NULL COMMENT '帳號',
  `pwd` varchar(20) NOT NULL COMMENT '密碼',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '帳號建立時間',
  `status` int NOT NULL,
  PRIMARY KEY (`adUserID`),
  UNIQUE KEY `UK_AdMember_account` (`account`)
) COMMENT='廣告會員';


CREATE TABLE `AdOrder` (
    `orderSN` int NOT NULL AUTO_INCREMENT COMMENT '訂單流水編號',
  `adUserID` int NOT NULL COMMENT '會員流水編號',
  `block` int NOT NULL COMMENT '版位',
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '成立時間',
  `showTime` timestamp NOT NULL COMMENT '開始時間',
  `expiredTime` timestamp NOT NULL COMMENT '結束時間',
  `status` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`orderSN`),
  KEY `AdOrder_adUerID_FK` (`adUserID`),
  CONSTRAINT `AdOrder_adUerID_FK` FOREIGN KEY (`adUserID`) REFERENCES `admember` (`adUserID`)
) COMMENT='廣告訂單';


CREATE TABLE `AdPic` (
  `adPicSN` int NOT NULL AUTO_INCREMENT COMMENT '圖片流水編號',
  `orderSN` int NOT NULL COMMENT '訂單流水編號',
  `pic` blob NOT NULL COMMENT '廣告圖片',
  PRIMARY KEY (`adPicSN`),
  CONSTRAINT `AdPic_adPicSN_FK` FOREIGN KEY (`orderSN`) REFERENCES `AdOrder` (`orderSN`)
) COMMENT='廣告圖片';

-- --------------------------------------FORUM----------------------------------------


create table `ArticleTitleOpt` (
	`articleTitleOptSN` int not null auto_increment comment '發文選項編號' primary key,
	`articleTitleOptText` char(12) not null comment '選項內容'
)AUTO_INCREMENT = 31 COMMENT='發文標題選項';

INSERT INTO ArticleTitleOpt(articleTitleOptText) VALUES ("關於潛水");
INSERT INTO ArticleTitleOpt(articleTitleOptText) VALUES ("新手須知");
INSERT INTO ArticleTitleOpt(articleTitleOptText) VALUES ("潛水心得");


create table `ForumArticle` (
	`articleSN` int not null auto_increment comment '文章編號' primary key,
	`articleTitle` varchar(60) not null comment '文章標題',
	-- 註解是原本的，下一行是因為要在DDL塞假資料改動的，到時候如果有問題請注意這邊
	-- `publishedDate` timestamp not null comment '發文時間',
	`publishedDate` timestamp not null default current_timestamp comment '發文時間',
	`articleText` longText not null comment '發文內容',
--	`articleStatus` int not null comment '文章狀態',
	-- 註解是更改資料欄位嘗試，下一行是更改後的作法。
	`articleStatus` boolean not null default 1 comment '文章狀態',
	`userID` int not null comment '會員編號',
	`articleTitleOptSN` int not null comment '發文選項編號',
	`rateGCount` int not null default '0' comment '文章好評',
	`rateNGCount` int not null default '0' comment '文章負評',
	CONSTRAINT `ForumArticle_userID` FOREIGN KEY (`userID`) REFERENCES `Member` (`userID`),
	CONSTRAINT `ForumArticle_articleTitleOptSN` FOREIGN KEY (`articleTitleOptSN`) REFERENCES `ArticleTitleOpt` (`articleTitleOptSN`)
)AUTO_INCREMENT = 30001 COMMENT='討論區文章';

INSERT INTO ForumArticle (articleTitle, articleText, articleStatus, userID, articleTitleOptSN, rateGCount, rateNGCount) VALUES ("關於浮潛", '浮潛 Snorkeling	為熱帶度假海域或海島極為普及的水上休閒活動，使用呼吸管在水面上游泳，不需要複雜的裝備，也不需要像水肺潛水那樣複雜訓練就可以在自然環境中觀賞水下生物，且不會受呼氣出來的氣泡干擾視線，但僅限深度約10公尺以內、生物豐富的海域，較能以浮潛欣賞海底之美。', 1, 1, 31, 87, 2);
INSERT INTO ForumArticle (articleTitle, articleText, articleStatus, userID, articleTitleOptSN, rateGCount, rateNGCount) VALUES ("新手須知的小事", '下水前請確保身心健康，感冒、鼻塞都不能下水。 潛水後24小時內不得搭飛機，空中低壓會導致低氧，可能會引發潛水伕病（減壓症），潛水前後一天也不建議飲酒。', 1, 2, 32, 20, 1);
INSERT INTO ForumArticle (articleTitle, articleText, articleStatus, userID, articleTitleOptSN, rateGCount, rateNGCount) VALUES ("福澤深厚湯姆貓可以潛水嗎？", 'as title', 1, 2, 33, 0, 99);

create table `ForumRate` (
	`articleRateSN` int not null auto_increment comment '文章評價編號' primary key,
	`userID` int not null comment '會員編號',
	`articleSN` int not null comment '文章編號',
--	`articleRate` tinyint(1) not null comment '評價',
	-- 更改資料欄位測試，下一行是新的。
	`articleRate` boolean not null default 0 comment '評價',
	CONSTRAINT `ForumRate_userID` FOREIGN KEY (`userID`) REFERENCES `Member` (`userID`),
	CONSTRAINT `ForumRate_articleSN` FOREIGN KEY (`articleSN`) REFERENCES `ForumArticle` (`articleSN`)
)AUTO_INCREMENT = 30000001 COMMENT='文章評價';

INSERT INTO ForumRate (userID, articleSN, articleRate) VALUES (3, 30001, 1);
INSERT INTO ForumRate (userID, articleSN, articleRate) VALUES (3, 30002, 0);


create table `ArticleReport` (
	`rptSN` int not null auto_increment comment '檢舉編號' primary key,
	`rptReason` varchar(150) not null comment '檢舉原因',
	`userID` int not null comment '會員編號',
	`articleSN` int not null comment '文章編號',
	`rptStatus` boolean not null default 0 comment '處理狀態',
	`reRptResult` varchar(150) comment '檢舉處理回報',
	CONSTRAINT `ArticleReport_userID` FOREIGN KEY (`userID`) REFERENCES `Member` (`userID`),
	CONSTRAINT `ArticleReport_articleSN` FOREIGN KEY (`articleSN`) REFERENCES `ForumArticle` (`articleSN`)
)AUTO_INCREMENT = 3001 COMMENT='文章檢舉';

INSERT INTO ArticleReport (userID, articleSN, rptReason) 
VALUES (4, 30003, "這來洗文章的吧？回去寫Java啦");
INSERT INTO ArticleReport (userID, articleSN, rptReason) 
VALUES (5, 30003, "根本沒內容的東西，佔版面");


create table `ForumComment` (
	`cmtSN` int not null auto_increment comment '留言編號' primary key,
	-- 註解是原本的，下一行是因為要在DDL塞假資料改動的，到時候如果有問題請注意這邊
	-- `cmtDate` timestamp not null comment '留言時間',
	`cmtDate` timestamp not null default current_timestamp comment '留言時間',
	`cmtText` varchar(150)not null comment '留言內容',
	`userID` int not null comment '會員編號',
	`articleSN` int not null comment '文章編號',
	CONSTRAINT `ForumComment_userID` FOREIGN KEY (`userID`) REFERENCES `Member` (`userID`),
	CONSTRAINT `ForumComment_articleSN` FOREIGN KEY (`articleSN`) REFERENCES `ForumArticle` (`articleSN`)
)AUTO_INCREMENT = 300001 COMMENT='討論區留言';

INSERT INTO ForumComment (cmtText, userID, articleSN) 
VALUES ("還是快回去寫Java吧！", 1, 30003);
INSERT INTO ForumComment (cmtText, userID, articleSN) 
VALUES ("沒看過會潛水的Tomcat...嫌自己命大嗎！高能", 2, 30003);


-- --------------------------------------孤兒們 QA MANAGER NEWS----------------------------------------

CREATE TABLE `QA` (
  `questionSN` int NOT NULL AUTO_INCREMENT COMMENT 'QA序號',
  `menu` char(1) NOT NULL COMMENT '選單分類',
  `submenu` char(2) NOT NULL COMMENT '選單子分類',
  `system` char(1) NOT NULL COMMENT '系統編號',
  `question` varchar(300) NOT NULL COMMENT '問題內容',
  `answer` varchar(300) NOT NULL COMMENT '回答內容',
  `clicks` int NOT NULL DEFAULT '0' COMMENT '點擊次數',
  `popularQuestion` tinyint NOT NULL COMMENT '熱門',
  `popularQuestionSort` int DEFAULT NULL COMMENT '熱門問題排序',
  PRIMARY KEY (`questionSN`)
) COMMENT='Q&A' AUTO_INCREMENT=6001;

INSERT INTO QA (menu, submenu, `system`, question, answer, popularQuestion) VALUES (1, 1, 1, '套裝行程訂購流程說明', '1. 請先登入/註冊會員。2. 選擇您欲報名的行程，點選我要報名，系統會自動判斷您的會員及潛水證照資格。3. 填寫訂單資訊，結帳，完成訂單確認。', false);
INSERT INTO QA (menu, submenu, `system`, question, answer, popularQuestion) VALUES (0, 0, 0, '就一句話！', '噁心！', true);


CREATE TABLE `Manager` (
  `account` varchar(50) NOT NULL COMMENT '帳號',
  `pwd` varchar(20) NOT NULL COMMENT '密碼',
  PRIMARY KEY (`account`)
) COMMENT='管理者';


CREATE TABLE `News` (
  `newsSN` int NOT NULL AUTO_INCREMENT COMMENT '新聞編號',
  `title` varchar(20) NOT NULL COMMENT '標題',
  `content` longtext NOT NULL COMMENT '內文',
  `image` longblob  COMMENT '照片',
  `newsDate` date NOT NULL COMMENT '新聞日期',
  `newsFrom` varchar(100) NOT NULL COMMENT '新聞來源',
  `newsType` char(1) NOT NULL COMMENT '新聞類型',
  PRIMARY KEY (`newsSN`)
) COMMENT='最新消息' AUTO_INCREMENT = 500001;

insert into News(title,content,image,newsdate,newsfrom,newstype)
values("澎湖水母群聚感染COVID-19","澎湖近期發生人傳水母武漢肺炎之症狀，還請各位潛水客避免前往以免感染",1,"2000-12-12","唬爛嘴","0");

