CREATE DATABASE IF NOT EXISTS UNDERWATER;
USE UNDERWATER;

DROP TABLE IF EXISTS `News`;
DROP TABLE IF EXISTS `Manager`;
DROP TABLE IF EXISTS `QA`;

DROP TABLE IF EXISTS `ArticleTitleOpt`; -- NOT YET

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
  `createTime` timestamp NOT NULL COMMENT '帳號建立時間',
  `status` int NOT NULL COMMENT '帳號狀態',
  `upDateTime` timestamp NOT NULL COMMENT '帳號更新時間',
  `ratePeople` int NOT NULL COMMENT '被評價總人數',
  `ratePoint` int NOT NULL COMMENT '被評價總分',
  PRIMARY KEY (`userID`),
  UNIQUE KEY `UK_MEMBER_account` (`account`)
) COMMENT='會員';



CREATE TABLE `Diveinfo` (
  `pointSN` int NOT NULL AUTO_INCREMENT COMMENT '潛點編號',
  `pointName` varchar(20) DEFAULT NULL,
  `latitude` double NOT NULL COMMENT '緯度',
  `longitude` double NOT NULL COMMENT '經度',
  `view` varchar(20) DEFAULT NULL,
  `introduction` longtext NOT NULL COMMENT '潛點圖文',
  `season` varchar(20) NOT NULL COMMENT '季節',
  `local` varchar(20) DEFAULT NULL,
  `pic` longblob NOT NULL COMMENT '預覽圖',
  `ratePoint` int NOT NULL COMMENT '評價總分',
  `ratePeople` int NOT NULL COMMENT '評價人數',
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`pointSN`)
) COMMENT='潛點資訊' AUTO_INCREMENT = 200001;


-- --------------------------------------套裝行程----------------------------------------


create table `GroupTour` (
	`groupTourSN` int NOT NULL AUTO_INCREMENT COMMENT '套裝行程編號',
    `tourName` varchar(30) not null comment '行程名稱',
    `startTime` date not null comment '行程起始日期',
    `endTime` date not null comment '行程結束日期',
    `regTime` date not null comment '報名開始日期',
    `closeTime` date not null comment '報名結束日期',
    `createTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP comment '行程建立日期',
    `pointSN` int not null comment '潛點編號',
    `price` int not null comment '行程價格',
    `attendNumber` int not null comment '參加人數',
    `limitNumder` int not null comment '限制人數',
    `certificationLimit` char(2) comment '資格限制',
    `status` char(1) not null comment '出團狀態',
    `content` longtext not null comment '行程圖文',
	CONSTRAINT GroupTour_pontSN_FK FOREIGN KEY (pointSN) REFERENCES DiveInfo (pointSN),
	PRIMARY KEY (`groupTourSN`)
) COMMENT='套裝行程' AUTO_INCREMENT=6001;


create table `Collections` (
	`userID` int not null comment '會員編號',
    `groupTourSN` int not null comment '套裝行程編號',
    CONSTRAINT Collections_userID_FK FOREIGN KEY (userID) REFERENCES Member (userID),
    CONSTRAINT Collections_groupTourSN_FK FOREIGN KEY (groupTourSN) REFERENCES GroupTour (groupTourSN)
) COMMENT='套裝行程收藏';


create table `OderForGroup` (
	`orderSN` int NOT NULL AUTO_INCREMENT COMMENT '套裝行程訂單編號',
	`userID` int not null comment '會員編號',
    `groupTourSN` int not null comment '套裝行程編號',
    `totalPrice` int not null comment '訂單總額',
	`purchaseDate`  date not null comment '購買日期',
    `phone` varchar(10) not null comment '手機',
    `personID` char(10) not null comment '身分證號',
    `birthdate` date not null comment '會員生日',
     CONSTRAINT OderForGroup_userID_FK FOREIGN KEY (userID) REFERENCES Member (userID),
     CONSTRAINT OderForGroup_groupTourSN_FK FOREIGN KEY (groupTourSN) REFERENCES GroupTour (groupTourSN),
	  PRIMARY KEY (`orderSN`)
) COMMENT='套裝行程訂單' AUTO_INCREMENT=6001;


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


-- --------------------------------------揪團----------------------------------------

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
  `createTime` timestamp NOT NULL COMMENT '揪團發文時間',
  `status` char(1) NOT NULL COMMENT '揪團狀態',
  PRIMARY KEY (`partySN`),
  KEY `FK_Party_partyHost` (`partyHost`),
  KEY `FK_Party_partyLocation` (`partyLocation`),
  CONSTRAINT `FK_Party_partyHost` FOREIGN KEY (`partyHost`) REFERENCES `Member` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Party_partyLocation` FOREIGN KEY (`partyLocation`) REFERENCES `DiveInfo` (`pointSN`) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT='揪團列表';


CREATE TABLE `PartyMember` (
  `partyMemberSN` int NOT NULL AUTO_INCREMENT COMMENT '揪團團員名單流水號',
  `partySN` int NOT NULL COMMENT '揪團編號',
  `partyMember` int NOT NULL COMMENT '團員會員編號',
  `gender` varchar(1) NOT NULL COMMENT '性別 0:男 1:女',
  `email` varchar(50) NOT NULL COMMENT 'Email',
  `phone` varchar(10) NOT NULL COMMENT '手機',
  `birthDate` date NOT NULL COMMENT '生日',
  `personID` char(10) NOT NULL COMMENT '身份證字號',
  `certificationPic` longblob COMMENT '證照圖片',
  `appliedDate` timestamp NOT NULL COMMENT '報名日期',
  `comment` varchar(1000) DEFAULT NULL COMMENT '備註',
  `status` int NOT NULL COMMENT '報名狀態 0:待審核 1:審核通過 2:審核未通過',
  PRIMARY KEY (`partyMemberSN`),
  UNIQUE KEY `UK_PartyMember_partySN_partyMember` (`partySN`,`partyMember`),
  KEY `FK_PartyMember_partyMember` (`partyMember`),
  CONSTRAINT `FK_PartyMember_partyMember` FOREIGN KEY (`partyMember`) REFERENCES `Member` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_PartyMember_partySN` FOREIGN KEY (`partySN`) REFERENCES `Party` (`partySN`) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT='揪團團員名單';


CREATE TABLE `MemberRate` (
  `SN` int NOT NULL AUTO_INCREMENT COMMENT '會員評價流水編號',
  `partySN` int DEFAULT NULL COMMENT '揪團編號',
  `orderSN` int DEFAULT NULL COMMENT '套裝行程訂單編號',
  `rateMaker` int NOT NULL COMMENT '評論方',
  `rateRecipiant` int NOT NULL COMMENT '被評論方',
  `rate` int NOT NULL COMMENT '評價',
  `rateDetail` varchar(3000) DEFAULT NULL COMMENT '評價詳細內容',
  `createTime` timestamp NOT NULL COMMENT '評價時間',
  PRIMARY KEY (`SN`),
  KEY `FK_MemberRate_partySN` (`partySN`),
  KEY `FK_MemberRate_orderSN` (`orderSN`),
  KEY `FK_rateMaker` (`rateMaker`),
  KEY `FK_rateRecipiant` (`rateRecipiant`),
  CONSTRAINT `FK_MemberRate_orderSN` FOREIGN KEY (`orderSN`) REFERENCES `GroupTour` (`groupTourSN`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_MemberRate_partySN` FOREIGN KEY (`partySN`) REFERENCES `Party` (`partySN`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_rateMaker` FOREIGN KEY (`rateMaker`) REFERENCES `Member` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_rateRecipiant` FOREIGN KEY (`rateRecipiant`) REFERENCES `Member` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE
) COMMENT='會員評價';


-- --------------------------------------商城相關表格----------------------------------------


CREATE TABLE `Product` (
  `productSN` int NOT NULL AUTO_INCREMENT COMMENT '商品編號',
  `productClass` varchar(2) NOT NULL COMMENT '商品類別',
  `productName` varchar(50) NOT NULL COMMENT '商品名稱',
  `productPrice` int NOT NULL COMMENT '商品單價',
  `productQuantity` int NOT NULL COMMENT '商品數量',
  `productStatus` char(1) NOT NULL COMMENT '商品狀態',
  `productDetail` longtext NOT NULL COMMENT '商品說明',
  `productCreateTime` timestamp NOT NULL DEFAULT NOW() COMMENT '上架時間',
  `productDiscount` tinyint(1) NOT NULL COMMENT '優惠品',
  `productPrime` tinyint(1) NOT NULL COMMENT '精選品',
  `ratingPoint` int NOT NULL COMMENT '評價總分數',
  `ratingNumber` int NOT NULL COMMENT '評價總人數',
  PRIMARY KEY (`productSN`),
  CONSTRAINT `product_chk_1` CHECK ((`productPrice` > 0))
) COMMENT='商品';

INSERT INTO Product (productClass,productName,productPrice,productQuantity,productStatus,
productDetail,productDiscount,productPrime,ratingPoint,ratingNumber)
values("蛙鞋","power牌鞋鞋",2000,100,0,"買到賺到",false,true,10,2);


CREATE TABLE `OrderForProduct` (
  `orderSN` int NOT NULL AUTO_INCREMENT COMMENT '訂單編號',
  `userID` int NOT NULL COMMENT '會員編號',
  `purchaseDate` timestamp NOT NULL DEFAULT NOW() COMMENT '購買時間',
  `totalPrice` int NOT NULL COMMENT '結帳總金額',
  `orderStatus` char(1) NOT NULL COMMENT '訂單狀態',
  `clearDate` timestamp NULL DEFAULT NULL COMMENT '完成時間',
  PRIMARY KEY (`orderSN`),
  KEY `FK_OrderForProduct_userID` (`userID`),
  CONSTRAINT `FK_OrderForProduct_userID` FOREIGN KEY (`userID`) REFERENCES `Member` (`userID`),
  CONSTRAINT `orderforproduct_chk_1` CHECK ((`totalPrice` > 0))
) COMMENT='商品訂單';

INSERT INTO OrderForProduct (userID,totalPrice,orderStatus)
values(2,100000000,1);


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
  CONSTRAINT `FK_OrderList_productSN` FOREIGN KEY (`productSN`) REFERENCES `Product` (`productSN`),
  CONSTRAINT `orderlist_chk_1` CHECK ((`productPrice` > 0))
) COMMENT='商品訂單明細';

INSERT INTO OrderList (productSN,orderSN,purchaseQuantity,productPrice,rating)
values(1,1,10,2000,8);


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
  CONSTRAINT `FK_ShoppingCar_userID` FOREIGN KEY (`userID`) REFERENCES `Member` (`userID`),
  CONSTRAINT `shoppingcar_chk_1` CHECK ((`productPrice` > 0)),
  CONSTRAINT `shoppingcar_chk_2` CHECK ((`totalPrice` > 0))
) COMMENT='購物車';

INSERT INTO ShoppingCar (userID,productSN,purchaseQuantity,productPrice,totalPrice)
values(1,1,3,2000,6000);


CREATE TABLE `ProductPhoto` (
  `photoSN` int NOT NULL AUTO_INCREMENT COMMENT '圖片流水號',
  `productSN` int NOT NULL COMMENT '商品編號',
  `productImages` longblob NOT NULL COMMENT '商品圖片',
  PRIMARY KEY (`photoSN`),
  KEY `FK_ProductPhoto_productSN` (`productSN`),
  CONSTRAINT `FK_ProductPhoto_productSN` FOREIGN KEY (`productSN`) REFERENCES `Product` (`productSN`)
) COMMENT='商品圖片';


-- --------------------------------------會員相關表格 CustomerReply Follow Chat----------------------------------------


create table `CustomerReply` (
	`customerReplySN` int not null AUTO_INCREMENT comment '訊息回覆編號',
	`userID` int not null comment '會員編號',
    `type` char(1) not null comment '類型',
    `content` varchar(500) not null comment '回應內容',
    `sendTime` timestamp not null comment '訊息傳送時間',
     CONSTRAINT CustomerReply_userID_FK FOREIGN KEY (userID) REFERENCES Member (userID),
	  PRIMARY KEY (`customerReplySN`)
) COMMENT='即時客服回應' AUTO_INCREMENT=60001;


CREATE TABLE `Follow` (
  `follower` int NOT NULL COMMENT '追蹤者編號',
  `followed` int NOT NULL COMMENT '被追蹤者編號',
  PRIMARY KEY (`follower`,`followed`),
  KEY `FK_Follow_followed` (`followed`),
  CONSTRAINT `FK_Follow_followed` FOREIGN KEY (`followed`) REFERENCES `member` (`userID`),
  CONSTRAINT `FK_Follow_follower` FOREIGN KEY (`follower`) REFERENCES `member` (`userID`)
) COMMENT='追蹤';


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


-- --------------------------------------AD----------------------------------------


CREATE TABLE `AdMember` (
  `adUserID` int NOT NULL AUTO_INCREMENT COMMENT '會員流水編號',
  `account` varchar(50) NOT NULL COMMENT '帳號',
  `pwd` varchar(20) NOT NULL COMMENT '密碼',
  `createTime` timestamp NOT NULL COMMENT '帳號建立時間',
  PRIMARY KEY (`adUserID`),
  UNIQUE KEY `UK_AdMember_account` (`account`)
) COMMENT='廣告會員';


CREATE TABLE `AdOrder` (
  `orderSN` int NOT NULL AUTO_INCREMENT COMMENT '訂單流水編號',
  `adUserID` int NOT NULL COMMENT '會員流水編號',
  `block` int NOT NULL COMMENT '版位',
  `time` timestamp NOT NULL COMMENT '成立時間',
  `showTime` timestamp NOT NULL COMMENT '開始時間',
  `expiredTime` timestamp NOT NULL COMMENT '結束時間',
  PRIMARY KEY (`orderSN`),
  KEY `AdOrder_adUerID_FK` (`adUserID`),
  CONSTRAINT `AdOrder_adUerID_FK` FOREIGN KEY (`adUserID`) REFERENCES `admember` (`adUserID`)
) COMMENT='廣告訂單';


CREATE TABLE `AdPic` (
  `adPicSN` int NOT NULL AUTO_INCREMENT COMMENT '圖片流水編號',
  `orderSN` int NOT NULL COMMENT '訂單流水編號',
  `pic` blob NOT NULL COMMENT '廣告圖片',
  PRIMARY KEY (`adPicSN`),
  CONSTRAINT `AdPic_adPicSN_FK` FOREIGN KEY (`adPicSN`) REFERENCES `AdOrder` (`orderSN`)
) COMMENT='廣告圖片';

-- --------------------------------------FORUM(有點問題還有4張)----------------------------------------


CREATE TABLE `ArticleTitleOpt` (
  `articleTitleOptSN` int NOT NULL AUTO_INCREMENT COMMENT '發文選項編號',
  `articleTitleOptText` char(12) NOT NULL COMMENT '選項內容',
  PRIMARY KEY (`articleTitleOptSN`)
) COMMENT='發文標題選項';



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


CREATE TABLE `Manager` (
  `account` varchar(50) NOT NULL COMMENT '帳號',
  `pwd` varchar(20) NOT NULL COMMENT '密碼',
  PRIMARY KEY (`account`)
) COMMENT='管理者';


CREATE TABLE `News` (
  `newsSN` int NOT NULL AUTO_INCREMENT COMMENT '新聞編號',
  `title` varchar(20) NOT NULL COMMENT '標題',
  `content` text NOT NULL COMMENT '內文',
  `image` longblob NOT NULL COMMENT '照片',
  `newsDate` date NOT NULL COMMENT '新聞日期',
  `newsFrom` varchar(20) NOT NULL COMMENT '新聞來源',
  `newsType` char(1) NOT NULL COMMENT '新聞類型',
  PRIMARY KEY (`newsSN`)
) COMMENT='最新消息' AUTO_INCREMENT = 500001;