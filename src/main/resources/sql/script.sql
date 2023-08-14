
-- Carga de ADMIN no banco de dados:
INSERT INTO public.funcionarios (matricula,data_admissao,data_nascimento,funcao,nome,perfil_funcionario,date_create,date_update,senha,email) VALUES
	 (999999,'1988-05-23','1956-12-08','condutor de Veículos','Almir','ADMIN','2023-08-10 13:35:14.248701','2023-08-10 13:35:14.248701','$2a$10$BiwvDvpIcGosgo7xrz327.15EIKT/eDaekL.s22E6ysG6uPsZhFcq','almir@gmail.com');

/* usuario e senha
{
	"email":"almir@gmail.com",
	"senha":"159"
}
*/

--Carga na Tabela de funcionarios:
insert
	into
	public.funcionarios (matricula,
	data_admissao,
	data_nascimento,
	funcao,
	nome,
	perfil_funcionario,
	date_create,
	date_update,
	senha,
	email)
values
(1,
'2023-05-23',
'1959-12-08',
'Analista Administrativo',
'Bianca Gigliotti Gonçalves',
'ADMIN',
'2023-08-10 13:35:14.248701',
'2023-08-10 13:35:14.248701',
'$2a$10$BiwvDvpIcGosgo7xrz327.15EIKT/eDaekL.s22E6ysG6uPsZhFcq',
'bicanca@gmail.com'),
(2,
'2023-05-23',
'1959-12-08',
'técnico de informática',
'José Gonçalves',
'PASSAGEIRO',
'2023-08-10 13:35:14.248701',
'2023-08-10 13:35:14.248701',
'$2a$10$BiwvDvpIcGosgo7xrz327.15EIKT/eDaekL.s22E6ysG6uPsZhFcq',
'jose@gmail.com'),
(3,
'2023-05-23',
'1959-12-08',
'assistente de RH',
'Lena Gonçalves',
'PASSAGEIRO',
'2023-08-10 13:35:14.248701',
'2023-08-10 13:35:14.248701',
'$2a$10$BiwvDvpIcGosgo7xrz327.15EIKT/eDaekL.s22E6ysG6uPsZhFcq',
'lena@gmail.com'),
(4,
'2023-05-23',
'1959-12-08',
'assistente de RH',
'Paulo Roberto',
'MOTORISTA',
'2023-08-10 13:35:14.248701',
'2023-08-10 13:35:14.248701',
'$2a$10$BiwvDvpIcGosgo7xrz327.15EIKT/eDaekL.s22E6ysG6uPsZhFcq',
'paulo@gmail.com'),
(5,
'2023-05-23',
'1959-12-08',
'assistente de contabilidade',
'Ivete Motini',
'PASSAGEIRO',
'2023-08-10 13:35:14.248701',
'2023-08-10 13:35:14.248701',
'$2a$10$BiwvDvpIcGosgo7xrz327.15EIKT/eDaekL.s22E6ysG6uPsZhFcq',
'ivete@gmail.com'),
(6,
'2023-05-23',
'1959-12-08',
'Advogada',
'Patricia Montini',
'PASSAGEIRO',
'2023-08-10 13:35:14.248701',
'2023-08-10 13:35:14.248701',
'$2a$10$BiwvDvpIcGosgo7xrz327.15EIKT/eDaekL.s22E6ysG6uPsZhFcq',
'patricia@gmail.com');

--Carga na tabela de Veículos
INSERT INTO public.veiculos (ano_fabricacao,fabricante,modelo,placa,possui_cacamba,quilometragem,total_passageiros,date_create,date_update) VALUES
	 (2023,'FORD','Ranger','IPO-1513',true,5000,4,'2023-08-14 10:53:12.391046','2023-08-14 10:53:12.391046'),
	 (2022,'FORD','Ka','IPP-7415',false,140000,4,'2023-08-10 16:11:11.382375','2023-08-10 16:11:11.382375'),
	 (2022,'FORD','Fiesta','TOP-2415',false,140000,4,'2023-08-10 16:15:38.008838','2023-08-10 16:15:38.008838'),
	 (2022,'FORD','Fusion','CAQ-2265',false,140000,4,'2023-08-14 10:52:19.454538','2023-08-14 10:52:19.454538');







