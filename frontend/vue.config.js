const { defineConfig } = require('@vue/cli-service')

//CKEditor
const path = require( 'path' );
const webpack = require( 'webpack' );
const { bundler, styles } = require( '@ckeditor/ckeditor5-dev-utils' );
const CKEditorWebpackPlugin = require( '@ckeditor/ckeditor5-dev-webpack-plugin' );
const TerserWebpackPlugin = require( 'terser-webpack-plugin' );


const target = 'http://localhost:9090'
module.exports = defineConfig(
{
    devServer: {
        port: 8080,
        proxy : {
        "/airreview/api/" : {target, changeOrigin: true},
        "/airreview/images" :{target, changeOrigin: true},
        "/airreview/article" :{target, changeOrigin: true},
        "/airreview/member/" :{target, changeOrigin: true},
        }
},
publicPath: '/airreview',
    transpileDependencies: [
    /ckeditor5-[^/\\]+[/\\]src[/\\].+\.js$/
],
  configureWebpack: {
    plugins: [
		new CKEditorWebpackPlugin( {
			language: 'ko',
			additionalLanguages: 'all'
		} ),
		new webpack.BannerPlugin( {
			banner: bundler.getLicenseBanner(),
			raw: true
		} )
	],
},

chainWebpack: config => {
    const svgRule = config.module.rule( 'svg' );
    svgRule.exclude.add( path.join( __dirname, 'node_modules', '@ckeditor' ) );
    config.module
        .rule( 'cke-svg' )
        .test( /ckeditor5-[^/\\]+[/\\]theme[/\\]icons[/\\][^/\\]+\.svg$/ )
        .use( 'raw-loader' )
        .loader( 'raw-loader' );

    //에디터 css 로딩
    config.module
        .rule( 'cke-css' )
        .test( /ckeditor5-[^/\\]+[/\\].+\.css$/ )
        .use( 'postcss-loader' )
        .loader( 'postcss-loader' )
        .tap( () => {
            return {
                postcssOptions: styles.getPostCssConfig( {   
                    minify: true
                } )
            };
        } );
}
  
})
