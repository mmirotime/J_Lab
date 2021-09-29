const CracoAlias = require('craco-alias');

module.exports = {
  plugins: [
    {
      plugin: CracoAlias,
      options: {
        source: 'tsconfig', // default: options, (옵션: jsconfig, tsconfig)
        tsConfigPath: 'tsconfig.paths.json', // source: 'tsconfig'일 때 path 설정 파일 경로 작성
      },
    },
  ],
};
