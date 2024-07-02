CREATE VIEW view_mat_ComposantApplicatif  AS
SELECT 
	 I_ID  "id", 
	 T_NAME_01 "Display Label", 
	 CASE
	 	WHEN I_STATUS_01 = 0 THEN 'en attente'
	 	WHEN I_STATUS_01 = 1 THEN 'en exploitation'
	 	WHEN I_STATUS_01 = 2 THEN 'réformé'
	 	WHEN I_STATUS_01 = 3 THEN 'réservé'
	 	WHEN I_STATUS_01 = 5 THEN 'en cours de réaffectation'
	 	WHEN I_STATUS_01 = 10 THEN 'supprimé'
	 	ELSE null
	 end as "Etat",
	 T_INFO_02 "Type de composant",
	 T_INFO_03 "Progiciel et version",
	 T_INFO_10 "Groupe responsable",
	 D_DATE_03 "Date de mise en service",
	 D_DATE_05 "Date de fin de garantie"
FROM ta_mat_material tmm 
WHERE i_type_01 =100;
