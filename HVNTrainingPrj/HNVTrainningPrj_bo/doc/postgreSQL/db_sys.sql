--
-- Host: localhost    Database: hnvTrainning
-- ------------------------------------------------------
--
-- Table structure for table TA_SYS_AUDIT
--

DROP TABLE IF EXISTS TA_SYS_AUDIT;
DROP TABLE IF EXISTS TA_SYS_EXCEPTION;
DROP TABLE IF EXISTS TA_SYS_LOCK;

CREATE TABLE TA_SYS_AUDIT (
  I_ID 			SERIAL PRIMARY KEY,
  I_Aut_User 	integer 	DEFAULT NULL,
  I_Val_01 		integer 	DEFAULT NULL ,
  I_Val_02 		integer 	DEFAULT NULL ,
  I_Val_03 		integer 	DEFAULT NULL,
  D_Date 	 	timestamp 	DEFAULT NULL,
  T_Info_01 	text  		DEFAULT NULL ,
  T_Info_02 	text  		DEFAULT NULL ,
  T_Info_03 	text  		DEFAULT NULL,
  T_Info_04 	text  		DEFAULT NULL
) ;

CREATE INDEX CONCURRENTLY idx_TSAUD_01 ON TA_SYS_AUDIT USING btree(I_Aut_User);
CREATE INDEX CONCURRENTLY idx_TSAUD_02 ON TA_SYS_AUDIT USING btree(I_Val_01);
CREATE INDEX CONCURRENTLY idx_TSAUD_03 ON TA_SYS_AUDIT USING btree(I_Val_02);
CREATE INDEX CONCURRENTLY idx_TSAUD_04 ON TA_SYS_AUDIT USING btree(I_Val_03);

COMMENT on column TA_SYS_AUDIT.I_Val_01  is 'entity type';
COMMENT on column TA_SYS_AUDIT.I_Val_02  is 'entity id';
COMMENT on column TA_SYS_AUDIT.I_Val_03  is '1:new, 2:mod, 3:del';
COMMENT on column TA_SYS_AUDIT.D_Date 	 is 'date creation';
COMMENT on column TA_SYS_AUDIT.T_Info_01 is 'entity content';
COMMENT on column TA_SYS_AUDIT.T_Info_02 is 'extra info';
  

CREATE TABLE TA_SYS_LOCK (
  I_ID 			SERIAL PRIMARY KEY,
  I_Aut_User 	integer 		DEFAULT NULL,
  I_Val_01 		integer 		DEFAULT NULL ,
  I_Val_02 		integer 		DEFAULT NULL ,
  I_Status 		integer 		DEFAULT NULL,
  D_Date_01 	timestamp 		DEFAULT NULL ,
  D_Date_02 	timestamp 		DEFAULT NULL ,
  T_Info_01 	varchar(500)  	DEFAULT NULL ,
  T_Info_02 	varchar(500)  	DEFAULT NULL 
) ;
CREATE INDEX CONCURRENTLY idx_TSLOC_01 ON TA_SYS_LOCK USING btree(I_Val_01);
CREATE INDEX CONCURRENTLY idx_TSLOC_02 ON TA_SYS_LOCK USING btree(I_Val_02);

COMMENT on column TA_SYS_LOCK.I_Val_01 	is 'object type, table reference';
COMMENT on column TA_SYS_LOCK.I_Val_02 	is 'object key: line id of object';
COMMENT on column TA_SYS_LOCK.D_Date_01 is 'date création of lock';
COMMENT on column TA_SYS_LOCK.D_Date_02 is 'date refresh of lock';
COMMENT on column TA_SYS_LOCK.T_Info_01 is 'user info';
COMMENT on column TA_SYS_LOCK.T_Info_02 is 'other info';

CREATE TABLE TA_SYS_EXCEPTION (
  I_ID 			SERIAL PRIMARY KEY,
  I_Aut_User 	integer 	 DEFAULT NULL,
  D_Date 		timestamp 	 DEFAULT NULL,
  T_Info_01 	varchar(200) DEFAULT NULL,
  T_Info_02 	varchar(200) DEFAULT NULL,
  T_Info_03 	varchar(200) DEFAULT NULL,
  T_Info_04 	text 		 DEFAULT NULL
) ;

CREATE INDEX CONCURRENTLY idx_TSEXC_01 ON TA_SYS_EXCEPTION USING btree(D_Date);

COMMENT on column TA_SYS_EXCEPTION.T_Info_01 	is 'T_Module';
COMMENT on column TA_SYS_EXCEPTION.T_Info_02 	is 'T_Class';
COMMENT on column TA_SYS_EXCEPTION.T_Info_03 	is 'T_Function';
COMMENT on column TA_SYS_EXCEPTION.T_Info_04 	is 'T_Error'

