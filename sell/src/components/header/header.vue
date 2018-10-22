<template>
  <div class="header">
    <div class="content-wrapper">
      <div class="avatar">
        <img :src="seller.avatar" width="64px" heigh="64px">
      </div>
      <div class="content">
        <div class="title">
          <span class="brand"></span>
          <span class="name">{{seller.name}}(大运村)</span>
        </div>
        <div class="description">
          {{seller.description}}/{{seller.deliveryTime}}分钟送达
        </div>
        <div class="supports" v-if="seller.supports">
          <span class="brand" :class="typeMap[seller.supports[0].type]"></span>
          <span class="description">{{seller.supports[0].description}}</span>
        </div>
      </div>
      <div class="supports-count" v-if="seller.supports" @click="showDetail=true">
          <span class="count">
            {{seller.supports.length}}个
          </span>
        <span class="icon-keyboard_arrow_right"></span>
      </div>

    </div>
    <div class="bulletin-wrapper" @click="showDetail=true">
      <div class="title"></div>
      <div class="bulletin">{{seller.bulletin}}</div>
      <div class="icon-keyboard_arrow_right"></div>
    </div>
    <div class="background">
      <img :src="seller.avatar" width="100%" height="100%">
    </div>
    <div class="detail" v-show="showDetail">
      <div class="detail-wrapper">
        <div class="detail-main">
          <div class="name">{{seller.name}}</div>
          <div class="star"></div>
          <div class="message">
            <div class="line"></div>
            <div class="text">优惠信息</div>
            <div class="line"></div>
          </div>
          <ul class="supports">
            <li class="support-item" v-for="support in seller.supports">
              <span class="icon" :class="typeMap[support.type]"></span>
              <span class="text">{{support.description}}</span>
            </li>
          </ul>
          <div class="message">
            <div class="line"></div>
            <div class="text">商家公告</div>
            <div class="line"></div>
          </div>
          <div class="advice">
            {{seller.bulletin}}
          </div>
        </div>
        <div class="close" @click="showDetail=false">
          <span class="icon-close"></span>
        </div>
      </div>
    </div>

  </div>
</template>

<script>
    export default {
        name: "header",
        data(){
          return{
            typeMap:[],
            showDetail:false
          }
        },
        props: {
          seller:{},
        },
        created(){
          this.typeMap=['decrease','discount','special','invoice','guarantee'];
        }
    }
</script>

<style lang="stylus">
 @import "../../common/stylus/background.styl"
    .header
      position relative
      height 134px
      width 100%
      background-color rgba(7,17,27,0.5)
      color rgb(255,255,255)
      .content-wrapper
        position relative
        display flex
        padding 24px 12px 18px 24px
        .avatar
          width 64px
          height 64px
          &>img
            border-radius 2px
        .content
          margin-left 16px
          width 100%
          height 100%
          .title
            padding-top 2px
            height 20px
            font-size 0
            .brand
              display inline-block
              width 30px
              height 18px
              bg-img('brand')
              background-size 30px 18px
            .name
              padding 0 0 0 6px
              display inline-block
              line-height 18px
              font-size 16px
              color rgb(255,255,255)
              font-weight bold
              text-align center
              height 100%
              vertical-align top
          .description
            padding 8px 0 10px 0
            line-height 12px;
            font-size 12px
            color rgb(255,255,255)
            font-weight 200
          .supports
            padding-bottom 2px
            font-size 0
            .brand
              display inline-block
              width 12px
              height 12px
              font-size 12px
              background-size 12px 12px
              vertical-align: top
              &.decrease
                bg-img('decrease_1')
            .description
              padding 0 0 0 4px
              font-size 10px
              color rgb(255,255,255)
              font-weight 200
              height 100%
              vertical-align top
        .supports-count
          position absolute
          right 12px
          bottom 10px
          height 24px
          border-radius 14px
          background-color rgba(0,0,0,0.2)
          padding 7px 8px
          .count
            display inline-block
            font-size 10px
            color rgb(255,255,255)
            font-weight 200
            line-height 12px
          .icon-keyboard_arrow_right
            display inline-block
            padding-left 2px
            font-size 10px
            color rgb(255,255,255)
            font-weight 200
            line-height 12px
      .bulletin-wrapper
        position relative
        display flex
        height 28px
        padding 0 12px
        background-color rgba(7,17,27,0.2)
        .title
          flex 0 0 22px
          height 100%
          margin-top 8px
          bg-img('bulletin')
          background-size 22px 12px
          background-repeat no-repeat
        .bulletin
          width 89%
          overflow hidden
          white-space nowrap
          font-size 10px
          font-weight 200
          line-height 28px
          padding-left 4px
          text-overflow ellipsis
        .icon-keyboard_arrow_right
          position absolute
          right 12px
          bottom 6px
          display inline-block
      .background
        position absolute
        left 0
        top 0
        z-index -1
        width 100%
        height 100%
        filter blur(10px)
      .detail
        position fixed
        top 0
        left 0
        z-index 200
        background-color rgba(7,17,27,0.8)
        backdrop-filter blur(10px)
        height 100%
        width 100%
        overflow auto
        .detail-wrapper
          position relative
          width 80%
          min-height 80%
          margin 0 auto
          padding-bottom 64px
          .detail-main
            margin-top 64px
            .name
              font-size 16px
              font-weight 700
              line-height 16px
              text-align center
            .star
              margin-top 16px
              height 24px
            .message
              display flex
              margin-top 28px
              .line
                flex 1 0 0
                border-1px(rgba(255,255,255,0.2))
                top -4px
              .text
                font-size 14px
                font-weight 700
                color rgb(255,255,255)
                line-height 14px
                padding 0 12px
              .line
                flex 1 0 0
            .supports
              margin-top 24px
              .support-item
                font-size 0
                margin 0 0 12px 12px
                &:last-child
                  margin-bottom 0
                .icon
                  display inline-block
                  width 16px
                  height 16px
                  background-size 16px 16px
                  vertical-align top
                  &.decrease
                    bg-img('decrease_2')
                  &.discount
                    bg-img('discount_2')
                  &.special
                    bg-img('special_2')
                  &.invoice
                    bg-img('invoice_2')
                  &.guarantee
                    bg-img('guarantee_2')
                .text
                  font-size 12px
                  font-weight 200
                  line-height 12px
                  vertical-align top
                  margin-left 6px



            .advice
              margin-top 24px
              font-size 12px
              font-weight 200
              line-height 24px
          .close
            display inline-block
            position absolute
            width 100%
            height 32px
            bottom 0
            left 0
            margin-bottom 32px
            text-align center
            .icon-close
              font-size 32px
              color rgba(255,255,255,0.5)




</style>
