insert into public.roles (id, role) values(1, 'ROLE_ADMIN');
insert into public.roles (id, role) values(2, 'ROLE_USER');

insert into public.users (name, login, password, email) values('admin', 'admin', 'admin', 'rcalculator2021@gmail.com');

insert into public.users_roles (user_id, role_id) values(1, 1);

insert into public.videos (title, url, genre, format) values('hello', '',
                                                             'greeting', 'mp4');

insert into public.videos (title, url, genre, format) values('violet', '',
                                                             'violet', 'mp4');