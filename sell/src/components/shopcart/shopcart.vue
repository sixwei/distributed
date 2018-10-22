<template>
  <div class="shopcart">
    <div class="content">
      <div class="content-left">
        <div class="logo-wrapper">
          <div class="logo">
            <div class="icon-shopping_cart"></div>
          </div>
        </div>
        <div class="money" :class="{'light':totalprice>0}">￥{{totalprice}}</div>
        <div class="fee">配送费{{deliveryPrice}}元</div>
      </div>
      <div class="content-right" :class="pay">{{textpay}}</div>
    </div>

  </div>

</template>

<script>
    export default {
        name: "shopcart",
      props:{
        minPrice:{
          type:Number,
          default:0
        },
        deliveryPrice:{
          type:Number,
          default:0
        },
        selectFoods:{
          type:Object,
          default(){
            return [
              {price:10,count:3}

            ]
          }

        }
      },

      computed:{
          totalprice(){
            let count =0
            this.selectFoods.forEach((food)=>{
              count+=food.price*food.count
            })
            return count
          },
        textpay(){
            if (this.totalprice===0||this.selectFoods.length===0){
              return `￥${this.minPrice}元起送`
            }else if (this.totalprice<this.minPrice){
              return `还差￥${this.minPrice-this.totalprice}起送`
            } else{
              return '去支付'
            }
        },
        pay(){
            if(this.totalprice>this.minPrice){
              return 'pay'
            }else{
              return 'no-pay'
            }
        }
      }
    }
</script>

<style lang="stylus" scoped>
  .shopcart
    position fixed
    left 0
    bottom 0
    width 100%
    height 48px
    background #141d27
    .content
      display flex
      box-sizing border-box
      .content-left
        flex 1
        height 100%
        width 100%
        .logo-wrapper
          display inline-block
          position relative
          font-size 0
          height 58px
          width 58px
          border-radius 50%
          top -10px
          background #141d27
          margin-left 18px
          padding 6px
          .logo
            height 44px
            width 44px
            border-radius 50%
            background #24343c
            text-align center
            margin-left 6px
            .icon-shopping_cart
              font-size 24px
              color rgba(255,255,255,0.2)
              line-height 44px
        .money
          display inline-block
          font-size 16px
          color rgba(255,255,255,0.4)
          line-height 24px
          font-weight 700
          margin 12px
          padding-right 12px
          height 100%
          vertical-align top
          border-right 1px solid rgba(255,255,255,0.1)
          &.light
            color rgb(255,255,255)
        .fee
          display inline-block
          font-size 12px
          color rgba(255,255,255,0.4)
          line-height 24px
          font-weight 700
          margin 12px 0
          height 100%
          vertical-align top
      .content-right
        flex 0 0 105px
        font-size 12px
        background-color rgba(255,255,255,0.4)
        font-weight 700
        line-height 24px
        padding 12px 8px
        text-align center
        &.pay
          background-color #99FF33


</style>
