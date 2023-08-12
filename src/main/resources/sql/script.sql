
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








