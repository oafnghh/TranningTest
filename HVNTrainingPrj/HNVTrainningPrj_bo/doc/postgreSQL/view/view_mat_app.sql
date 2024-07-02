CREATE VIEW view_mat_Application  AS
SELECT 
	 I_ID  "id", 
	 T_NAME_01 "Display Label", 
	 T_CODE_01 "Code Appli", 
	 CASE
	    WHEN I_TYPE_02 = 10101 THEN 'nouvelle'
	    WHEN I_TYPE_02 = 10102 THEN 'evolution'
	    WHEN I_TYPE_02 = 10103 THEN 'migration tech'
	    ELSE null
	 end as "Type", 
	 CASE
	 	WHEN I_STATUS_01 = 0 THEN 'en attente'
	 	WHEN I_STATUS_01 = 1 THEN 'en exploitation'
	 	WHEN I_STATUS_01 = 2 THEN 'réformé'
	 	WHEN I_STATUS_01 = 3 THEN 'réservé'
	 	WHEN I_STATUS_01 = 5 THEN 'en cours de réaffectation'
	 	WHEN I_STATUS_01 = 10 THEN 'supprimé'
	 	ELSE null
	 end as "Etat", 
	 T_INFO_01 "Description", 
	 T_INFO_02 "MOE",
	 T_INFO_03 "MOA",
	 D_DATE_03 "Date de mise en service",
	 D_DATE_05 "Date de fin de garantie"
FROM ta_mat_material tmm 
WHERE i_type_01 =10;