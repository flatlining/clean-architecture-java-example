function fn() {
    var APP_HTTPS = 'app.server.https';
    var APP_DOMAIN = 'app.server.domain';
    var APP_PORT = 'app.server.port';
    var APP_BASE_URL = 'app.server.baseUrl';

    var env = karate.env; // get java system property 'karate.env'
    karate.log('karate.env system property was:', env);
    if (!env) {
        env = 'dev'; // a custom 'intelligent' default
    }

    var port = karate.properties[APP_PORT];
    if (!port) {
        port = 8080;
    }

    var domain = karate.properties[APP_DOMAIN];
    if (!domain) {
        domain = 'localhost';
    }

    var protocol = 'http';
    if (karate.properties[APP_HTTPS] == 'true') {
        protocol = 'https';
        karate.configure('ssl', true);
    }

    var baseUrl = karate.properties[APP_BASE_URL];
    if (!baseUrl) {
        baseUrl = protocol + '://' + domain + ':' + port;
    } else if (baseUrl.startsWith('https://')) {
        karate.log('ssl is true.');
        karate.configure('ssl', true);
    }

    var config = {
        baseUrl: baseUrl
    };

    karate.configure('connectTimeout', 5000);
    karate.configure('readTimeout', 5000);

    karate.log('karate.config is:', config);

    return config;
}
