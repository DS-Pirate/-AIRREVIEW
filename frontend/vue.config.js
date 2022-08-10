const { defineConfig } = require('@vue/cli-service')

//CKEditor
const path = require( 'path' );
const CKEditorWebpackPlugin = require( '@ckeditor/ckeditor5-dev-webpack-plugin' );
const { styles } = require( '@ckeditor/ckeditor5-dev-utils' );

module.exports = defineConfig({
  transpileDependencies: [
    /ckeditor5-[^/\\]+[/\\]src[/\\].+\.js$/
],
  configureWebpack: {
    plugins: [
        // CKEditor 웹팩 플러그인 섹션
        new CKEditorWebpackPlugin( {
            language: 'ko',

            // Append translations to the file matching the `app` name.
            translationsOutputFile: /app/
        } )
    ]
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
