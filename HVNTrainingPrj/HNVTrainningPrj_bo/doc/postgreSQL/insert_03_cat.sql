INSERT INTO public.ta_tpy_category
(i_id, t_name, t_code, t_info, i_status, i_type_01, i_type_02, i_type_03, i_parent, i_per_manager)
VALUES(2, 'LISTE MACHINE DEV', 'LMD', 'Il existe différents types de machines selon leur fonction, leur source d’énergie, leur mode de fonctionnement et leur domaine d’application. Voici quelques exemples de types de machines :', 1, 100, 102, NULL, NULL, NULL);

INSERT INTO public.ta_tpy_category
(i_id, t_name, t_code, t_info, i_status, i_type_01, i_type_02, i_type_03, i_parent, i_per_manager)
VALUES(3, 'LISTE MACHINE PROD', 'LMP', 'Il existe différents types de machines selon leur fonction, leur source d’énergie, leur mode de fonctionnement et leur domaine d’application. Voici quelques exemples de types de machines :', 1, 100, 103, NULL, NULL, NULL);

INSERT INTO public.ta_tpy_category
(i_id, t_name, t_code, t_info, i_status, i_type_01, i_type_02, i_type_03, i_parent, i_per_manager)
VALUES(1, 'CMDB', 'CMDB', 'Une CMDB est une base de données de gestion de configuration, qui permet de répertorier et de gérer les actifs informatiques d’une entreprise. Une CMDB contient des informations sur les éléments de configuration (CI), qui sont les composants du système informatique, tels que les logiciels, le matériel, la documentation, etc. Une CMDB permet aussi de visualiser les relations entre les CI, afin de comprendre leur organisation et leur dépendance. Une CMDB est un outil essentiel pour la gestion des services informatiques, basée sur le référentiel ITIL.', 1, 100, 101, NULL, NULL, NULL);

INSERT INTO public.ta_tpy_category
(i_id, t_name, t_code, t_info, i_status, i_type_01, i_type_02, i_type_03, i_parent, i_per_manager)
VALUES(4, 'ROADMAP', 'ROADMAP', '​​​​​​​La feuille de route (ROADMAP) se présente sous la forme d''un fichier XLS (voir section suivante). <br/><br/>
Ce document, uniquement à usage interne RATP,  permet d''avoir accès aux informations suivantes :  <br/>
- la liste des composants pris en charge par l''ingénierie de briques techniques OnPremise FAB, ventilée sur les différents onglets du fichier : OS, SGBD, ...  <br/>
- la date prévisionnelle de mise à disposition des composants techniques,  <br/><br/>
<b><i>Remarque importante :</i></b> les jalons présentés dans ce document ne sont pas contractuels. En cas d''enjeux forts sur l''un d''entre eux, contacter systématiquement vos correspondants FAB pour valider la cohérence entre votre planning projet et les jalons de livraison des composants.   ', 1, 100, 1, NULL, NULL, NULL);