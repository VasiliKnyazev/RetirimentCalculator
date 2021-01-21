insert into public.roles (id, role) values(1, 'ROLE_ADMIN');
insert into public.roles (id, role) values(2, 'ROLE_USER');

insert into public.users (name, login, password, email) values('admin', 'admin', 'admin', 'rcalculator2021@gmail.com');

insert into public.users_roles (user_id, role_id) values(1, 1);

insert into public.videos (title, url, genre, format) values('coffee', 'https://vod-progressive.akamaized.net/exp=1611245613~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F3611%2F17%2F443057031%2F1940243865.mp4~hmac=9a3d91c58ecd7d41ca1604801df741bc2858ef704629dd165166531045bbbde2/vimeo-prod-skyfire-std-us/01/3611/17/443057031/1940243865.mp4?filename=Coffee+-+45358.mp4',
                                                             'meal', 'mp4');

insert into public.videos (title, url, genre, format) values('bees', 'https://vod-progressive.akamaized.net/exp=1611245668~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F4197%2F16%2F420985147%2F1818842482.mp4~hmac=740ac93691dd161d9daef27d62b29298130dbdd5d44a2d3e8478a11995997419/vimeo-prod-skyfire-std-us/01/4197/16/420985147/1818842482.mp4?filename=Bee+-+39116.mp4',
                                                             'insects', 'mp4');

insert into public.videos (title, url, genre, format) values('city', 'https://vod-progressive.akamaized.net/exp=1611245690~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F279%2F19%2F476396222%2F2126680870.mp4~hmac=2d74e54738c335ba278dddd75e298748f1e4b65233156f2878d0375a71f8df3a/vimeo-prod-skyfire-std-us/01/279/19/476396222/2126680870.mp4?filename=Trafic+-+53902.mp4',
                                                             'city', 'mp4');

insert into public.videos (title, url, genre, format) values('beach', 'https://vod-progressive.akamaized.net/exp=1611245981~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F4516%2F18%2F472583432%2F2105253874.mp4~hmac=cacc1d428a49c2740b257a58d1c3d7f38cfbfc3024b91dd4f5a78056f6c2aff8/vimeo-prod-skyfire-std-us/01/4516/18/472583432/2105253874.mp4?filename=Sea+-+53127.mp4',
                                                             'nature', 'mp4');

insert into public.videos (title, url, genre, format) values('waterfall', 'https://vod-progressive.akamaized.net/exp=1611246003~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F2645%2F16%2F413229662%2F1776913803.mp4~hmac=ac7c6c31a7232435acce6e0921d893423b05232827832cf9af846a4da843ead5/vimeo-prod-skyfire-std-us/01/2645/16/413229662/1776913803.mp4?filename=Waterfall+-+37088.mp4',
                                                             'nature', 'mp4');

insert into public.videos (title, url, genre, format) values('river', 'https://vod-progressive.akamaized.net/exp=1611246055~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F2882%2F17%2F439412962%2F1919710884.mp4~hmac=2c62e5b81f7c492caa5fc9a7bfb06e91107efca1552593d6807590b2c71edfe1/vimeo-prod-skyfire-std-us/01/2882/17/439412962/1919710884.mp4?filename=River+-+44509.mp4',
                                                             'city', 'mp4');

insert into public.videos (title, url, genre, format) values('cycling', 'https://vod-progressive.akamaized.net/exp=1611246081~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F4204%2F16%2F421020269%2F1819002288.mp4~hmac=1f55fe506d3a0b27aac2a61b6a068fa3a9b3e3206b80ae5309063f3dc69aa247/vimeo-prod-skyfire-std-us/01/4204/16/421020269/1819002288.mp4?filename=Cycling+-+39183.mp4',
                                                             'city', 'mp4');

insert into public.videos (title, url, genre, format) values('cat', 'https://vod-progressive.akamaized.net/exp=1611246102~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F4044%2F16%2F420224623%2F1814595284.mp4~hmac=2f52911bc1eea2ddbf735105078271c5c3671426843bc6f5f3d5666d6ffdc388/vimeo-prod-skyfire-std-us/01/4044/16/420224623/1814595284.mp4?filename=Cat+-+39009.mp4',
                                                             'pets', 'mp4');

insert into public.videos (title, url, genre, format) values('moon', 'https://vod-progressive.akamaized.net/exp=1611246149~acl=%2Fvimeo-prod-skyfire-std-us%2F01%2F3195%2F19%2F490977203%2F2205504601.mp4~hmac=50fa82240469130032754f52996768bdeb4596b305382fc02e95970408b89516/vimeo-prod-skyfire-std-us/01/3195/19/490977203/2205504601.mp4?filename=Moon+-+59026.mp4',
                                                             'nature', 'mp4');

/*insert into public.videos (title, url, genre, format) values('title', '',
                                                             'genre', 'mp4');*/