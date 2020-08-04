module.exports = {
  "transpileDependencies": [
    "vuetify"
  ],
  outputDir: 'dist',
  assetsDir: 'static',
  devServer: {
    host: '0.0.0.0',
    port: 80,
    // proxy for back-end
    proxy:
    {
      '/': {
        target: process.env.VUE_APP_PROXYURL,
        changeOrigin: true
      }
    }

  }
}