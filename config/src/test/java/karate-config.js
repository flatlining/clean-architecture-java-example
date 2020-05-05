function fn() {
    var env = karate.env; // get java system property 'karate.env'
    karate.log('karate.env system property was:', env);
    if (!env) {
        env = 'dev'; // a custom 'intelligent' default
    }

    var port = karate.properties['app.server.port'];
    if (!port) {
        port = 8080;
    }

    var domain = karate.properties['app.server.domain'];
    if (!domain) {
        domain = 'localhost';
    }

    var protocol = 'http';
    if (karate.properties['app.server.https'] == 'true') {
        protocol = 'https';
        karate.configure('ssl', true);
    }

    var baseUrl = karate.properties['app.server.baseUrl'];
    if (!baseUrl) {
        baseUrl = protocol + '://' + domain + ':' + port;
    }

    var config = {
        baseUrl: baseUrl
    };

    karate.configure('connectTimeout', 5000);
    karate.configure('readTimeout', 5000);

    karate.log('karate.config is:', config);

    return config;
}
