--
-- Host: localhost    Database: hnvTrainning
-- ------------------------------------------------------



--
-- Table structure for table `TA_SYS_AUDIT`
--

DROP TABLE IF EXISTS `TA_SYS_AUDIT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TA_SYS_AUDIT` (
  `I_ID` int(11) NOT NULL AUTO_INCREMENT,
  `I_Aut_User` int(11) DEFAULT NULL,
  `I_Val_01` int(11) DEFAULT NULL COMMENT 'entity type',
  `I_Val_02` int(11) DEFAULT NULL COMMENT 'entity id',
  `I_Val_03` int(11) DEFAULT NULL COMMENT '1:new, 2:mod, 3:del',
  `D_Date` datetime DEFAULT NULL,
  `T_Val_01` text  DEFAULT NULL,
  `T_Val_02` text  DEFAULT NULL,
  `T_Val_03` text  DEFAULT NULL,
  `T_Val_04` text  DEFAULT NULL,
  PRIMARY KEY (`I_ID`),
  UNIQUE KEY `idx_TSAUD_00` (`I_ID`),
  KEY `idx_TSAUD_01` (`I_Aut_User`),
  KEY `idx_TSAUD_02` (`I_Val_01`),
  KEY `idx_TSAUD_03` (`I_Val_02`),
  KEY `idx_TSAUD_04` (`I_Val_03`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TA_SYS_LOCK`
--

DROP TABLE IF EXISTS `TA_SYS_LOCK`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TA_SYS_LOCK` (
  `I_ID` int(11) NOT NULL AUTO_INCREMENT,
  `I_Aut_User` int(11) DEFAULT NULL,
  `I_Val_01` int(11) DEFAULT NULL COMMENT 'object type, table reference',
  `I_Val_02` int(11) DEFAULT NULL COMMENT 'object key: line id of object',
  `I_Status` int(11) DEFAULT NULL,
  `D_Date_01` datetime DEFAULT NULL COMMENT 'date création of lock',
  `D_Date_02` datetime DEFAULT NULL COMMENT 'date refresh of lock',
  `T_Val_01` varchar(500)  DEFAULT NULL,
  `T_Val_02` varchar(500)  DEFAULT NULL,
  PRIMARY KEY (`I_ID`),
  UNIQUE KEY `idx_TSLOC_00` (`I_ID`),
  KEY `idx_TSLOC_01` (`I_Val_01`),
  KEY `idx_TSLOC_02` (`I_Val_02`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `TA_SYS_EXCEPTION`
--

DROP TABLE IF EXISTS `TA_SYS_EXCEPTION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TA_SYS_EXCEPTION` (
  `I_ID` int(11) NOT NULL AUTO_INCREMENT,
  `I_Aut_User` int(11) DEFAULT NULL,
  `D_Date` datetime DEFAULT NULL,
  `T_Module` varchar(200) DEFAULT NULL,
  `T_Class` varchar(200) DEFAULT NULL,
  `T_Function` varchar(200) DEFAULT NULL,
  `T_Error` text DEFAULT NULL,
  PRIMARY KEY (`I_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;

--
-- Table structure for table `TA_AUT_AUTHORIZATION`
--


CREATE TABLE `TA_AUT_RIGHT` (
  `I_ID` int(11) NOT NULL COMMENT 'cac quyen co the: xem, xoa, sua, them moi, in an, xem bao cao...',
  `T_Name` varchar(200) NOT NULL,  
  `T_Description` text DEFAULT NULL,
  `T_Group` varchar(100) DEFAULT NULL, 
  PRIMARY KEY (`I_ID`),
  UNIQUE KEY `idx_TARIG_01` (`T_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `TA_AUT_ROLE` (
  `I_ID` int(11) NOT NULL,
  `T_Name` varchar(100) NOT NULL,   
  `T_Description` text DEFAULT NULL,
  `T_Aut_Right` longtext DEFAULT NULL COMMENT 'id cac quyen lien quan, vd: 12001, 12003',
  PRIMARY KEY (`I_ID`),
  UNIQUE KEY `idx_TAROL_01` (`T_Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `TA_AUT_AUTHORIZATION` (
  `I_ID` int(11) NOT NULL AUTO_INCREMENT,
  `I_Aut_User` int(11) DEFAULT NULL,
  `I_Aut_Role` int(11) DEFAULT NULL,
  `T_Aut_Right` longtext DEFAULT NULL COMMENT 'id cac quyen lien quan, vd: 12001, 12003, copy lai tu TA_AUT_ROLE.T_Aut_Right',
  PRIMARY KEY (`I_ID`),
  UNIQUE KEY `idx_TAUAU_01` (`I_ID`),
  KEY `idx_TAUAU_02` (`I_Aut_User`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


--
-- Table structure for table `TA_AUT_USER`
--

DROP TABLE IF EXISTS `TA_AUT_USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TA_AUT_USER` (
  `I_ID` int(11) NOT NULL AUTO_INCREMENT,
  `I_Status` int(11) DEFAULT NULL,
  
  `I_Type_01` int(11) DEFAULT NULL,
  `I_Type_02` int(11) DEFAULT NULL,
   
  `T_Login_01` varchar(100)  DEFAULT NULL,
  `T_Login_02` varchar(100)  DEFAULT NULL,
  `T_Pass_01` varchar(1000)  DEFAULT NULL,
  `T_Pass_02` varchar(1000)  DEFAULT NULL,
  
  `T_Info_01` text  DEFAULT NULL COMMENT 'email',
  `T_Info_02` text  DEFAULT NULL,
  `T_Info_03` text  DEFAULT NULL,
  `T_Info_04` text  DEFAULT NULL,
  `T_Info_05` text  DEFAULT NULL,
  
  `D_Date_01` datetime DEFAULT NULL COMMENT 'dt new',
  `D_Date_02` datetime DEFAULT NULL COMMENT 'dt mod',
  `D_Date_03` datetime DEFAULT NULL	COMMENT 'dt limit if need validation',
  `I_Aut_User_01` int(11) DEFAULT NULL COMMENT 'id user new ',
  `I_Aut_User_02` int(11) DEFAULT NULL COMMENT 'id user mod ',
  `I_Aut_User_03` int(11) DEFAULT NULL COMMENT 'id user sup ',
  
  `I_Per_Person` int(11) DEFAULT NULL,
  `I_Per_Manager` int(11) DEFAULT NULL,
  
  PRIMARY KEY (`I_ID`),
  UNIQUE KEY `idx_TUSER_01` (`I_ID`),
  KEY `idx_TUSER_02` (`T_Login_01`),
  KEY `idx_TUSER_03` (`T_Login_02`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `TA_PER_PERSON`
--

DROP TABLE IF EXISTS `TA_PER_PERSON`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TA_PER_PERSON` (
  `I_ID` int(11) NOT NULL AUTO_INCREMENT,
  `I_Status` int(11) DEFAULT NULL,
  `I_Type_01` int(11) DEFAULT NULL,
  `I_Type_02` int(11) DEFAULT NULL,
  `T_Name_01` varchar(500)  NOT NULL,
  `T_Name_02` varchar(500)  DEFAULT NULL,
  `T_Name_03` varchar(500)  DEFAULT NULL,
  `T_Code_01` varchar(500)  DEFAULT NULL,
  `T_Code_02` varchar(500)  DEFAULT NULL,
  `T_Code_03` varchar(500)  DEFAULT NULL,
  `T_Info_01` text  DEFAULT NULL,
  `T_Info_02` text  DEFAULT NULL,
  `T_Info_03` text  DEFAULT NULL,
  `T_Info_04` text  DEFAULT NULL,
  `T_Info_05` text  DEFAULT NULL,
  `D_Date_01` datetime DEFAULT NULL,
  `D_Date_02` datetime DEFAULT NULL,
  `D_Date_03` datetime DEFAULT NULL,
  `I_Aut_User_01` int(11) DEFAULT NULL,
  `I_Aut_User_02` int(11) DEFAULT NULL,
  `I_Per_Manager` int(11) DEFAULT NULL,
  PRIMARY KEY (`I_ID`),
  UNIQUE KEY `idx_TPERS_01` (`I_ID`),
  KEY `idx_TPERS_02` (`T_Name_01`),
  KEY `idx_TPERS_03` (`T_Name_02`),
  KEY `idx_TPERS_04` (`T_Code_01`),
  KEY `idx_TPERS_05` (`T_Code_02`),
  KEY `idx_TPERS_06` (`I_Type_01`),
  KEY `idx_TPERS_07` (`I_Type_02`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;




--
-- Table structure for table `TA_TPY_DOCUMENT`
--

DROP TABLE IF EXISTS `TA_TPY_DOCUMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TA_TPY_DOCUMENT` (
  `I_ID` int(11) NOT NULL AUTO_INCREMENT,
  `I_Entity_Type` int(11) DEFAULT NULL,
  `I_Entity_ID` int(11) DEFAULT NULL,
  `I_Status` int(11) DEFAULT NULL COMMENT '0: public, 1: private friend, 2: private self ',
  `I_Priority` int(11) DEFAULT NULL COMMENT 'order of file in list if needed',
  `I_Type_01` int(11) DEFAULT NULL COMMENT '1: media, 2: other',
  `I_Type_02` int(11) DEFAULT NULL COMMENT '1: avatar, 2: img, 3: video, 10: all',
  `I_Type_03` int(11) DEFAULT NULL COMMENT '1: public, 2: private',
  `F_Val_01` double DEFAULT NULL COMMENT 'file size',
  `F_Val_02` double DEFAULT NULL COMMENT 'other',
  `T_Info_01` text DEFAULT NULL COMMENT 'filename',
  `T_Info_02` text DEFAULT NULL COMMENT 'path real in server',
  `T_Info_03` text DEFAULT NULL COMMENT 'path url',
  `T_Info_04` text DEFAULT NULL COMMENT 'path real preview',
  `T_Info_05` text DEFAULT NULL COMMENT 'path url preview',
  `T_Info_06` text DEFAULT NULL,
  `T_Info_07` text DEFAULT NULL,
  `T_Info_08` text DEFAULT NULL,
  `T_Info_09` text DEFAULT NULL COMMENT 'comment',
  `T_Info_10` text DEFAULT NULL COMMENT 'path tmp',
  `D_Date_01` datetime DEFAULT NULL COMMENT 'Date new',
  `D_Date_02` datetime DEFAULT NULL COMMENT 'Date mod',
  `I_Aut_User_01` int(11) DEFAULT NULL COMMENT 'user new',
  `I_Aut_User_02` int(11) DEFAULT NULL COMMENT 'user mod',
  `I_Parent_ID` int(11) DEFAULT NULL COMMENT 'tpyDocument origin id when this doc is duplicated',
  PRIMARY KEY (`I_ID`),
  UNIQUE KEY `idx_TTDOC_00` (`I_ID`),
  KEY `idx_TTDOC_01` (`I_Type_01`),
  KEY `idx_TTDOC_02` (`I_Type_02`),
  KEY `idx_TTDOC_03` (`D_Date_01`),
  KEY `idx_TTDOC_04` (`I_Entity_Type`,`I_Entity_ID`),
  KEY `idx_TTDOC_05` (`T_Info_01`(768))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `TA_TPY_INFORMATION`
--

DROP TABLE IF EXISTS `TA_TPY_INFORMATION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TA_TPY_INFORMATION` (
  `I_ID` 			int(11) NOT NULL AUTO_INCREMENT,
  `I_Entity_Type` 	int(11) DEFAULT NULL,
  `I_Entity_ID` 	int(11) DEFAULT NULL,
  `I_Status` 		int(11) DEFAULT NULL,
  `I_Priority` 		int(11) DEFAULT NULL COMMENT 'order of file in list if needed',
  `I_Type_01` int(11) DEFAULT NULL,
  `I_Type_02` int(11) DEFAULT NULL,
  `I_Type_03` int(11) DEFAULT NULL,
  `T_Info_01` text  DEFAULT NULL,
  `T_Info_02` text  DEFAULT NULL,
  `T_Info_03` text  DEFAULT NULL,
  `T_Info_04` text  DEFAULT NULL,
  `T_Info_05` text  DEFAULT NULL,
  `T_Info_06` text  DEFAULT NULL,
  `T_Info_07` text  DEFAULT NULL,
  `T_Info_08` text  DEFAULT NULL,
  `T_Info_09` text  DEFAULT NULL,
  `T_Info_10` text  DEFAULT NULL,
  `F_Val_01` double DEFAULT NULL,
  `F_Val_02` double DEFAULT NULL,
  `F_Val_03` double DEFAULT NULL,
  `F_Val_04` double DEFAULT NULL,
  `F_Val_05` double DEFAULT NULL,
  `F_Val_06` double DEFAULT NULL,
  `F_Val_07` double DEFAULT NULL,
  `F_Val_08` double DEFAULT NULL,
  `F_Val_09` double DEFAULT NULL,
  `F_Val_10` double DEFAULT NULL,
  `D_Date_01` datetime DEFAULT NULL COMMENT 'Date new',
  `D_Date_02` datetime DEFAULT NULL COMMENT 'Date mod',
  `I_Aut_User_01` int(11) DEFAULT NULL COMMENT 'user new',
  `I_Aut_User_02` int(11) DEFAULT NULL COMMENT 'user mod',
  PRIMARY KEY (`I_ID`),
  UNIQUE KEY `idx_TTINF_00` (`I_ID`),
  KEY `idx_TTINF_01` (`I_Type_01`),
  KEY `idx_TTINF_02` (`I_Type_02`),
  KEY `idx_TTINF_03` (`I_Type_03`),
  KEY `idx_TTINF_04` (`D_Date_01`),
  KEY `idx_TTINF_06` (`I_Entity_Type`,`I_Entity_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

CREATE TABLE `TA_TPY_CATEGORY` (
  `I_ID` int(11) NOT NULL AUTO_INCREMENT, 
  `I_Per_Manager` int(11) NOT NULL,
  `T_Name` varchar(100) NOT NULL,
  `T_Code` varchar(45) DEFAULT NULL,
  `T_Description` text DEFAULT NULL,
  `I_Type_00` int(11) NOT NULL COMMENT 'what the table will use this category, ex: I_Type_00= ID_TA_MAT_MATERIAL ',
  `I_Type_01` int(11) DEFAULT NULL,
  `I_Type_02` int(11) DEFAULT NULL,
  `I_Parent`  int(11) DEFAULT NULL COMMENT 'the cat parent',
  PRIMARY KEY (`I_ID`),
  KEY `idx_TTCAT_01` (`I_Per_Manager`),
  KEY `idx_TTCAT_02` (`I_Type_00`),
  KEY `idx_TTCAT_03` (`I_Type_01`),
  KEY `idx_TTCAT_04` (`I_Type_02`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `TA_TPY_CATEGORY_ENTITY` (
  `I_ID` 			int(11) NOT NULL AUTO_INCREMENT,
  `I_Tpy_Category` 	int(11) NOT NULL,
  `I_Entity_Type` 	int(11) NOT NULL,
  `I_Entity_ID` 	int(11) NOT NULL, 
  PRIMARY KEY (`I_ID`),
  KEY `idx_TTCEN_01` (`I_Entity_Type`,`I_Entity_ID`),
  KEY `idx_TTCEN_02` (`I_Tpy_Category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `TA_NSO_POST` (
  `I_ID` int(11) NOT NULL AUTO_INCREMENT,
  `T_Ref` varchar(45) DEFAULT NULL,
  `T_Title` varchar(1000) DEFAULT NULL,
 
  `I_Type_01` int(11) DEFAULT NULL,
  `I_Type_02` int(11) DEFAULT NULL,
  `I_Type_03` int(11) DEFAULT NULL,
  `I_Status`  int(11) DEFAULT NULL,
  `T_Comment` text DEFAULT NULL COMMENT 'use in adm mode',
  
  `T_Content_01` text DEFAULT NULL,
  `T_Content_02` text DEFAULT NULL,
  `T_Content_03` text DEFAULT NULL,
  `T_Content_04` text DEFAULT NULL,
  `T_Content_05` text DEFAULT NULL,
  `T_Content_06` text DEFAULT NULL,
  `T_Content_07` text DEFAULT NULL,
  `T_Content_08` text DEFAULT NULL,
  `T_Content_09` text DEFAULT NULL,
  `T_Content_10` text DEFAULT NULL,
  
  `F_Eval_01` double DEFAULT NULL,
  `F_Eval_02` double DEFAULT NULL,
  `F_Eval_03` double DEFAULT NULL,
  `F_Eval_04` double DEFAULT NULL,
  `F_Eval_05` double DEFAULT NULL,
  
  `T_Property_01` text DEFAULT NULL,
  `T_Property_02` text DEFAULT NULL,
  `T_Property_03` text DEFAULT NULL,
  `T_Property_04` text DEFAULT NULL,
  
  
  `D_Date_New` datetime DEFAULT NULL,
  `D_Date_Mod` datetime DEFAULT NULL,
  `I_Aut_User_New` int(11) DEFAULT NULL,
  `I_Aut_User_Mod` int(11) DEFAULT NULL,
  
  `I_Nb_Resp` int(11) DEFAULT NULL,
  `I_Parent` int(11) DEFAULT NULL COMMENT 'id of main post for that this post response',
  `I_Entity_Type` int(11) DEFAULT NULL COMMENT 'type offer, area ....',
  `I_Entity_ID` int(11) DEFAULT NULL COMMENT 'offer id, area id...',
  
  PRIMARY KEY (`I_ID`),
  KEY `idx_TNPOS_01` (`D_Date_New`),
  KEY `idx_TNPOS_02` (`I_Aut_User_New`),
  KEY `idx_TNPOS_03` (`I_Type_01`),
  KEY `idx_TNPOS_05` (`I_Entity_Type`,`I_Entity_ID`),
  KEY `idx_TNPOS_06` (`I_Parent`),
  CONSTRAINT `fk_TNPOS_01` FOREIGN KEY (`I_Parent`) REFERENCES `TA_NSO_POST` (`I_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


/* insert main manager */
INSERT INTO TA_PER_PERSON
(I_ID, I_Status, I_Type_01, I_Type_02, T_Name_01, T_Name_02, T_Code_01, T_Code_02, T_Info_01, T_Info_02, T_Info_03, T_Info_04, T_Info_05, D_Date_01, D_Date_02, D_Date_03, I_Aut_User_01, I_Aut_User_02, I_Per_Manager)
VALUES(1, 1, 1000001, 1010010, 'HNV', 'HNV', 'HNV', 'HNV', 'DN', '(+84)', 'contact@hnv.com', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1);

INSERT INTO TA_PER_PERSON
(I_ID, I_Status, I_Type_01, I_Type_02, T_Name_01, T_Name_02, T_Code_01, T_Code_02, T_Info_01, T_Info_02, T_Info_03, T_Info_04, T_Info_05, D_Date_01, D_Date_02, D_Date_03, I_Aut_User_01, I_Aut_User_02, I_Per_Manager)
VALUES(2, 1, 1, 1, 'Adm', 'Adm', 'Adm', 'Adm', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1, 1);

/* insert adm user */
INSERT INTO TA_AUT_USER (I_Status,I_Type_01,T_Login_01,T_Pass_01,T_Info_01,T_Info_02,T_Info_03,T_Info_04,T_Info_05,D_Date_01,D_Date_02,D_Date_03,I_Aut_User_01,I_Aut_User_02,I_Aut_User_03,I_Per_Manager, I_Per_Person) VALUES 
(1,2,'adm','df0217a29bdf4b725d1e531d87075cf536120a38b9eb1ceaffc84e8c22cf33d3','Admin','hnv@hnv-tech.com',NULL,NULL,NULL,NULL,'2022-03-07 22:06:44.000',NULL,NULL,1,1,1, 2)
/*pass: nvbh*/
