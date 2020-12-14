module.exports = {
    devServer: {
        proxy: {
            '^/tasks': {
                target: 'http://localhost:9080/tasks-backend-1.0-SNAPSHOT/api',
                changeOrigin: true
            }
        }
    }
};
