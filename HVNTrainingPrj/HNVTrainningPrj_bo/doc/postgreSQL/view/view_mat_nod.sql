CREATE VIEW view_mat_Node  AS
SELECT 
	 I_ID  "id", 
	 T_NAME_01 "Display Label", 
	 CASE
	    WHEN I_TYPE_02 = 30101 THEN 'A'
	    WHEN I_TYPE_02 = 30102 THEN 'B'
	    WHEN I_TYPE_02 = 30103 THEN 'C'
	    WHEN I_TYPE_02 = 30104 THEN 'D'
	    ELSE null
	 end as "Type d'offre", 
	 CASE
	 	WHEN I_STATUS_01 = 0 THEN 'en attente'
	 	WHEN I_STATUS_01 = 1 THEN 'en exploitation'
	 	WHEN I_STATUS_01 = 2 THEN 'réformé'
	 	WHEN I_STATUS_01 = 3 THEN 'réservé'
	 	WHEN I_STATUS_01 = 5 THEN 'en cours de réaffectation'
	 	WHEN I_STATUS_01 = 10 THEN 'supprimé'
	 	ELSE null
	 end as "Etat", 
	 CASE
	 	WHEN I_TYPE_02 = 50100 THEN 'SP'
	 	WHEN I_TYPE_02 = 50200 THEN 'VM'
	 	ELSE null
	 end as "Specificites du serveur", 
	 CASE
	 	WHEN I_TYPE_03 = 50100 THEN 'Linux'
	 	WHEN I_TYPE_03 = 50200 THEN 'Win'
	 	WHEN I_TYPE_03 = 50300 THEN 'Vmware'
	 	ELSE null
	 end as "Categorie", 

	 T_INFO_01 "Description", 
	 T_INFO_02 "DiscoveredOsVersion",
	 T_INFO_03 "OsDescription",
	 T_INFO_04 "NodeModel",
	 T_INFO_10 "Groupe responsable", 
	 D_DATE_03 "Date de mise en service",
	 D_DATE_04 "Date de fin en service",
	 D_DATE_05 "Date de fin de garantie"
FROM ta_mat_material tmm 
WHERE i_type_01 =50;
