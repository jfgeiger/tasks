const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
    app.use(
        '/tasks',
        createProxyMiddleware({
            target: 'http://localhost:9080/tasks-backend-1.0-SNAPSHOT/api',
            changeOrigin: true,
        })
    );
};
