const { defineConfig } = require('@vue/cli-service')

const target = 'http://localhost:9090'
module.exports = defineConfig(
{
    configureWebpack: {
        performance: {
            maxEntrypointSize: 5120000,
            maxAssetSize: 5120000
        }
    },
    devServer: {
        port: 8080,
        proxy : {
        "/airreview/api/" : {target, changeOrigin: true},
        "/airreview/images" :{target, changeOrigin: true},
        "/airreview/article" :{target, changeOrigin: true},
        "/airreview/member" :{target, changeOrigin: true},
        }
},
publicPath: '/airreview',
    transpileDependencies: [
    /ckeditor5-[^/\\]+[/\\]src[/\\].+\.js$/
],
})
