-- --------------------------------------------------------------------------
-- --------------------------------------------------------------------------
-- --------------------------------------------------------------------------
--
-- Table structure for table TA_TPY_DOCUMENT
--
DROP TABLE IF EXISTS TA_TPY_DOCUMENT;
DROP TABLE IF EXISTS TA_TPY_FAVORITE;
DROP TABLE IF EXISTS TA_TPY_INFORMATION;
DROP TABLE IF EXISTS TA_TPY_CATEGORY;
DROP TABLE IF EXISTS TA_TPY_CATEGORY_ENTITY;
DROP TABLE IF EXISTS TA_TPY_TRANSLATION;

CREATE TABLE TA_TPY_DOCUMENT (
  I_ID 				SERIAL PRIMARY KEY,
  I_Entity_Type 	integer DEFAULT NULL,
  I_Entity_ID 		integer DEFAULT NULL,
 
  I_Priority 		integer DEFAULT NULL,
  I_Parent 			integer DEFAULT NULL,
  
  I_Status 	integer DEFAULT NULL,
   
  I_Type_01 integer DEFAULT NULL,
  I_Type_02 integer DEFAULT NULL,
  I_Type_03 integer DEFAULT NULL,
  I_Type_04 integer DEFAULT NULL,
  I_Type_05 integer DEFAULT NULL,
  
  F_Val_01 DOUBLE PRECISION DEFAULT NULL ,
  F_Val_02 DOUBLE PRECISION DEFAULT NULL ,
  F_Val_03 DOUBLE PRECISION DEFAULT NULL ,
  F_Val_04 DOUBLE PRECISION DEFAULT NULL ,
  F_Val_05 DOUBLE PRECISION DEFAULT NULL ,
  
  T_Info_01 text DEFAULT NULL ,
  T_Info_02 text DEFAULT NULL ,
  T_Info_03 text DEFAULT NULL ,
  T_Info_04 text DEFAULT NULL ,
  T_Info_05 text DEFAULT NULL,
  T_Info_06 text DEFAULT NULL,
  T_Info_07 text DEFAULT NULL,
  T_Info_08 text DEFAULT NULL,
  T_Info_09 text DEFAULT NULL,
  T_Info_10 text DEFAULT NULL,
 
  D_Date_01 timestamp DEFAULT NULL,
  D_Date_02 timestamp DEFAULT NULL,
  D_Date_03 timestamp DEFAULT NULL,
  D_Date_04 timestamp DEFAULT NULL,
  D_Date_05 timestamp DEFAULT NULL,
  
  I_Aut_User_01 integer DEFAULT NULL,
  I_Aut_User_02 integer DEFAULT NULL
) ;

CREATE INDEX CONCURRENTLY idx_TTDOC_00 ON TA_TPY_DOCUMENT USING btree(I_Entity_Type,I_Entity_ID);
CREATE INDEX CONCURRENTLY idx_TTDOC_01 ON TA_TPY_DOCUMENT USING btree(I_Type_01);
CREATE INDEX CONCURRENTLY idx_TTDOC_02 ON TA_TPY_DOCUMENT USING btree(I_Type_02);
CREATE INDEX CONCURRENTLY idx_TTDOC_03 ON TA_TPY_DOCUMENT USING btree(I_Type_03);
CREATE INDEX CONCURRENTLY idx_TTDOC_10 ON TA_TPY_DOCUMENT USING btree(I_Parent);

COMMENT on column TA_TPY_DOCUMENT.I_Priority 	is 'order of file in list if needed';
COMMENT on column TA_TPY_DOCUMENT.I_Parent 		is 'tpyDocument origin id when this doc is duplicated';
  
COMMENT on column TA_TPY_DOCUMENT.I_Status 		is '0: new, 1: ok, 5: review, 10: del ';
   
COMMENT on column TA_TPY_DOCUMENT.I_Type_01 	is '1: media, 2: other';
COMMENT on column TA_TPY_DOCUMENT.I_Type_02 	is '1: avatar, 2: img, 3: video, 10: all';
COMMENT on column TA_TPY_DOCUMENT.I_Type_03 	is '1: public, 2: private';
COMMENT on column TA_TPY_DOCUMENT.I_Type_04 	is 'other';
COMMENT on column TA_TPY_DOCUMENT.I_Type_05 	is 'other';
  
COMMENT on column TA_TPY_DOCUMENT.F_Val_01 	is 'file size';
COMMENT on column TA_TPY_DOCUMENT.F_Val_02 	is 'other';
COMMENT on column TA_TPY_DOCUMENT.F_Val_03 	is 'other';
COMMENT on column TA_TPY_DOCUMENT.F_Val_04 	is 'other';
COMMENT on column TA_TPY_DOCUMENT.F_Val_05 	is 'other';
  
COMMENT on column TA_TPY_DOCUMENT.T_Info_01 	is 'filename';
COMMENT on column TA_TPY_DOCUMENT.T_Info_02 	is 'path real in server';
COMMENT on column TA_TPY_DOCUMENT.T_Info_03 	is 'path url';
COMMENT on column TA_TPY_DOCUMENT.T_Info_04 	is 'path real preview';
COMMENT on column TA_TPY_DOCUMENT.T_Info_05 	is 'path url preview';
COMMENT on column TA_TPY_DOCUMENT.T_Info_06 	is '';
COMMENT on column TA_TPY_DOCUMENT.T_Info_07 	is '';
COMMENT on column TA_TPY_DOCUMENT.T_Info_08 	is '';
COMMENT on column TA_TPY_DOCUMENT.T_Info_09 	is 'comment';
COMMENT on column TA_TPY_DOCUMENT.T_Info_10 	is 'path tmp';
 
COMMENT on column TA_TPY_DOCUMENT.D_Date_01 	is 'Date new';
COMMENT on column TA_TPY_DOCUMENT.D_Date_02 	is 'Date mod';
COMMENT on column TA_TPY_DOCUMENT.D_Date_03 	is 'Date begin';
COMMENT on column TA_TPY_DOCUMENT.D_Date_04 	is 'Date end';
COMMENT on column TA_TPY_DOCUMENT.D_Date_05 	is 'Date other';
  
COMMENT on column TA_TPY_DOCUMENT.I_Aut_User_01 	is 'user new';
COMMENT on column TA_TPY_DOCUMENT.I_Aut_User_02 	is 'user mod';

--
-- Table structure for table TA_TPY_INFORMATION
--


CREATE TABLE TA_TPY_INFORMATION (
  I_ID 				SERIAL PRIMARY KEY,
  
  I_Entity_Type 	integer DEFAULT NULL,
  I_Entity_ID 		integer DEFAULT NULL,
  I_Priority 		integer DEFAULT NULL,
  
  I_Status  integer DEFAULT NULL,
  I_Type_01 integer DEFAULT NULL,
  I_Type_02 integer DEFAULT NULL,
  I_Type_03 integer DEFAULT NULL,
  I_Type_04 integer DEFAULT NULL,
  I_Type_05 integer DEFAULT NULL,
  
  T_Info_01 text  DEFAULT NULL,
  T_Info_02 text  DEFAULT NULL,
  T_Info_03 text  DEFAULT NULL,
  T_Info_04 text  DEFAULT NULL,
  T_Info_05 text  DEFAULT NULL,
  T_Info_06 text  DEFAULT NULL,
  T_Info_07 text  DEFAULT NULL,
  T_Info_08 text  DEFAULT NULL,
  T_Info_09 text  DEFAULT NULL,
  T_Info_10 text  DEFAULT NULL,
  
  T_Info_11 text DEFAULT NULL,
  T_Info_12 text DEFAULT NULL,
  T_Info_13 text DEFAULT NULL,
  T_Info_14 text DEFAULT NULL,
  T_Info_15 text DEFAULT NULL,
  T_Info_16 text DEFAULT NULL,
  T_Info_17 text DEFAULT NULL,
  T_Info_18 text DEFAULT NULL,
  T_Info_19 text DEFAULT NULL,
  T_Info_20 text DEFAULT NULL,
  
  F_Val_01 DOUBLE PRECISION DEFAULT NULL,
  F_Val_02 DOUBLE PRECISION DEFAULT NULL,
  F_Val_03 DOUBLE PRECISION DEFAULT NULL,
  F_Val_04 DOUBLE PRECISION DEFAULT NULL,
  F_Val_05 DOUBLE PRECISION DEFAULT NULL,
  F_Val_06 DOUBLE PRECISION DEFAULT NULL,
  F_Val_07 DOUBLE PRECISION DEFAULT NULL,
  F_Val_08 DOUBLE PRECISION DEFAULT NULL,
  F_Val_09 DOUBLE PRECISION DEFAULT NULL,
  F_Val_10 DOUBLE PRECISION DEFAULT NULL,
  
  F_Val_11 DOUBLE PRECISION DEFAULT NULL,
  F_Val_12 DOUBLE PRECISION DEFAULT NULL,
  F_Val_13 DOUBLE PRECISION DEFAULT NULL,
  F_Val_14 DOUBLE PRECISION DEFAULT NULL,
  F_Val_15 DOUBLE PRECISION DEFAULT NULL,
  F_Val_16 DOUBLE PRECISION DEFAULT NULL,
  F_Val_17 DOUBLE PRECISION DEFAULT NULL,
  F_Val_18 DOUBLE PRECISION DEFAULT NULL,
  F_Val_19 DOUBLE PRECISION DEFAULT NULL,
  F_Val_20 DOUBLE PRECISION DEFAULT NULL,
  
  D_Date_01 timestamp DEFAULT NULL,
  D_Date_02 timestamp DEFAULT NULL,
  
  I_Aut_User_01 integer DEFAULT NULL,
  I_Aut_User_02 integer DEFAULT NULL
) ;

CREATE INDEX CONCURRENTLY idx_TTINF_00 ON TA_TPY_INFORMATION USING btree(I_Entity_Type,I_Entity_ID);
CREATE INDEX CONCURRENTLY idx_TTINF_01 ON TA_TPY_INFORMATION USING btree(I_Type_01);
CREATE INDEX CONCURRENTLY idx_TTINF_02 ON TA_TPY_INFORMATION USING btree(I_Type_02);
CREATE INDEX CONCURRENTLY idx_TTINF_03 ON TA_TPY_INFORMATION USING btree(I_Type_03);

COMMENT on column TA_TPY_INFORMATION.I_Priority	is 'order of file in list if needed';
COMMENT on column TA_TPY_INFORMATION.D_Date_01	is 'Date new';
COMMENT on column TA_TPY_INFORMATION.D_Date_02	is 'Date mod';
  
COMMENT on column TA_TPY_INFORMATION.I_Aut_User_01	is 'user new';
COMMENT on column TA_TPY_INFORMATION.I_Aut_User_02	is 'user mod';

CREATE TABLE TA_TPY_TRANSLATION (
  I_ID 			SERIAL PRIMARY KEY,
  I_Entity_Type integer NOT NULL,
  I_Entity_ID 	integer NOT NULL,
  
  I_Val_01 		integer DEFAULT NULL,
  I_Val_02 		integer DEFAULT NULL,
   
  T_Info_01 	text DEFAULT NULL,
  T_Info_02 	text DEFAULT NULL
) ;
CREATE INDEX CONCURRENTLY idx_TTTRA_01 ON TA_TPY_TRANSLATION USING btree(I_Entity_Type,I_Entity_ID);

COMMENT on column TA_TPY_TRANSLATION.I_Val_01	is 'lang option';
COMMENT on column TA_TPY_TRANSLATION.I_Val_02	is 'other option';

CREATE TABLE TA_TPY_FAVORITE (
  I_ID 			SERIAL PRIMARY KEY,
  I_Aut_User 	integer NOT NULL,
  I_Entity_Type integer NOT NULL,
  I_Entity_ID 	integer NOT NULL,
 
  D_Date 		timestamp DEFAULT NULL,
  
  T_Title 		varchar(1000) DEFAULT NULL,
  T_Description text DEFAULT NULL,
  
  I_Type 		integer DEFAULT NULL,
  I_Status 		integer DEFAULT NULL,
  I_Priority 	integer DEFAULT NULL 
) ;
CREATE INDEX CONCURRENTLY idx_TTFAV_01 ON TA_TPY_FAVORITE USING btree(I_Entity_Type,I_Entity_ID);
CREATE INDEX CONCURRENTLY idx_TTFAV_02 ON TA_TPY_FAVORITE USING btree(I_Aut_User);

COMMENT on column TA_TPY_FAVORITE.D_Date		is 'date creation';
COMMENT on column TA_TPY_FAVORITE.I_Priority	is 'order of display';

CREATE TABLE TA_TPY_CATEGORY (
  I_ID 				SERIAL PRIMARY KEY, 
  
  T_Name 			varchar(500) NOT NULL,
  T_Code 			varchar(100) DEFAULT NULL,
  T_Info 			text DEFAULT NULL,
  
  I_Status 			integer DEFAULT NULL,
  I_Type_01 		integer NOT NULL,
  I_Type_02 		integer DEFAULT NULL,
  I_Type_03 		integer DEFAULT NULL,
  
  I_Parent  		integer DEFAULT NULL,
  I_Per_Manager 	integer DEFAULT NULL
) ;
CREATE INDEX CONCURRENTLY idx_TTCAT_01 ON TA_TPY_CATEGORY USING btree(T_Name);
CREATE INDEX CONCURRENTLY idx_TTCAT_02 ON TA_TPY_CATEGORY USING btree(T_Code);
CREATE INDEX CONCURRENTLY idx_TTCAT_03 ON TA_TPY_CATEGORY USING btree(I_Parent);
CREATE INDEX CONCURRENTLY idx_TTCAT_11 ON TA_TPY_CATEGORY USING btree(I_Type_01);
CREATE INDEX CONCURRENTLY idx_TTCAT_12 ON TA_TPY_CATEGORY USING btree(I_Type_02);
CREATE INDEX CONCURRENTLY idx_TTCAT_13 ON TA_TPY_CATEGORY USING btree(I_Type_03);

COMMENT on column TA_TPY_CATEGORY.I_Type_01		is 'what the table will use this category, ex: I_Type_00= ID_TA_MAT_MATERIAL ';
COMMENT on column TA_TPY_CATEGORY.I_Parent		is 'the cat parent';

CREATE TABLE TA_TPY_CATEGORY_ENTITY (
  I_ID 				SERIAL PRIMARY KEY,
  I_Tpy_Category 	integer NOT NULL,
  I_Entity_Type 	integer NOT NULL,
  I_Entity_ID 		integer NOT NULL
) ;

CREATE INDEX CONCURRENTLY idx_TTCEN_01 ON TA_TPY_CATEGORY_ENTITY USING btree(I_Entity_Type,I_Entity_ID);
CREATE INDEX CONCURRENTLY idx_TTCEN_02 ON TA_TPY_CATEGORY_ENTITY USING btree(I_Tpy_Category);

