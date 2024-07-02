CREATE VIEW view_mat_Application_DEXP  AS
SELECT 
	 m.I_ID  "App id", 
	 m.T_NAME_01 "App Display Label", 
	 m.T_CODE_01 "App Code Appli", 
	 CASE
	    WHEN m.I_TYPE_02 = 10101 THEN 'nouvelle'
	    WHEN m.I_TYPE_02 = 10102 THEN 'evolution'
	    WHEN m.I_TYPE_02 = 10103 THEN 'migration tech'
	    ELSE null
	 end as "App Type", 
	 CASE
	 	WHEN m.I_STATUS_01 = 0 THEN 'en attente'
	 	WHEN m.I_STATUS_01 = 1 THEN 'en exploitation'
	 	WHEN m.I_STATUS_01 = 2 THEN 'réformé'
	 	WHEN m.I_STATUS_01 = 3 THEN 'réservé'
	 	WHEN m.I_STATUS_01 = 5 THEN 'en cours de réaffectation'
	 	WHEN m.I_STATUS_01 = 10 THEN 'supprimé'
	 	ELSE null
	 end as "App Etat", 
	
	 m.T_INFO_02 "App MOE",
	 m.T_INFO_03 "App MOA",
	 
	 d.T_INFO_03 "DEXP URL"
	
FROM ta_mat_material m inner join ta_tpy_document d
on d.i_entity_type = 20000 and d.i_entity_id = m.i_id and m.i_type_01 =10;