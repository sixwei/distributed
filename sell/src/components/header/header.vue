<template>
  <div class="header">
      <div class="content-wrapper">
        <div class="avatar">
          <img :src="seller.avatar" alt="">
        </div>
        <div class="content">
          <div class="up">
            <div class="band">品牌</div>
            <div class="seller-name">{{seller.name}}</div>
          </div>
        </div>
        <div class="supports"></div>
      </div>
  </div>
</template>

<script>
    export default {
        name: "header",
        props: {
          seller:{}

        }
    }


</script>

<style lang="stylus">
    .header
      height 134px
      width 100%
      .content-wrapper
        display flex
        position relative
        right -24px
        top 24px
        .avatar
          &>img
            display inline-block
            width 64px
            height 64px
            border-radius 4px
        .content
          position relative
          margin-left 16px
          margin-up 4px
          .up
            .band
              background-color rgba(7,17,27,0.5)
              blur 5px
            .seller-name
              font-size 18px
              color rgb(255,255,255)
              font-weight bold
              line-height 18px


</style>
