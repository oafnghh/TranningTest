CREATE VIEW view_mat_ServiceApplicatif  AS
SELECT 
	 I_ID  "id", 
	 T_NAME_01 "Display Label", 
	 T_NAME_02 "Label", 
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
        WHEN i_status_03 = 0 THEN 'non'::text
        WHEN i_status_03 = 1 THEN 'oui'::text
        ELSE NULL::text
     END AS "QoS",
	 CASE
	 	WHEN I_STATUS_04 = 0 THEN 'non'
	 	WHEN I_STATUS_04 = 1 THEN 'oui'
	 	ELSE null
	 end as "Service Mutualisé", 
	 CASE
	 	WHEN I_STATUS_05 = 0 THEN 'non'
	 	WHEN I_STATUS_05 = 1 THEN 'oui'
	 	ELSE null
	 end as "Astreinte", 
	 T_INFO_01 "Description", 
	 T_INFO_02 "Diffusion sans indisponibilité",
	 T_INFO_03 "Diffusion si indisponibilité",
	 T_INFO_04 "Jour de service  : Heure Ouverture : Fermeture  aux utilisateurs",
	 
	 T_INFO_10 "Groupe responsable", 
	 D_DATE_04 "Date Entree Astreinte",
	 D_DATE_03 "Date de mise en service",
	 D_DATE_05 "Date de fin de garantie"
FROM ta_mat_material tmm 
WHERE i_type_01 =80;
