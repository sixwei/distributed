<template>
    <div class="goods">
      <div class="menu-wrapper" ref="menuWrapper">
        <ul class="menu">
          <li class="menu-item" v-for="(item,index) in goods" @click="selectmenu(index)" :class="{'current':currentIndex===index}">
            <span class="text">
              <span class="icon" v-if="item.type > 0" :class="typeMap[item.type]"></span>{{item.name}}
            </span>
          </li>
        </ul>
      </div>
      <div class="foods-wrapper" ref="foodsWrapper">
        <ul>
          <li v-for="good in goods" class="foods-list-hook">
            <div class="title">{{good.name}}</div>
            <ul class="foods">
              <li v-for="food in good.foods" class="food">
                <div class="content-wrapper border-1px">
                  <div class="avatar">
                    <img :src="food.icon" width="57px" height="57px">
                  </div>
                  <div class="describe">
                    <h1 class="name">{{food.name}}</h1>
                    <p class="description">{{food.description}}</p>
                    <div class="count-rating">
                      <span class="sellCount">月售{{food.sellCount}}份</span>
                      <span class="rating">好评率{{food.rating}}%</span>
                    </div>
                    <div class="price">
                      <span class="new">￥{{food.price}}</span>
                      <span class="old" v-if="food.oldPrice">￥{{food.oldPrice}}</span>
                    </div>
                  </div>
                </div>
              </li>
            </ul>
          </li>
        </ul>
      </div>
      <Shopcart :minPrice="seller.minPrice" :deliveryPrice="seller.deliveryPrice"></Shopcart>
    </div>
</template>

<script>
  import BScroll from 'better-scroll'
  import Shopcart from "../shopcart/shopcart";

    export default {
      name: "goods",
      components: {Shopcart},
      data(){
          return{
            goods:[],
            typeMap:[],
            listHeight:[],
            scrollY:0
          }
      },
      props:{
        seller:{
          type:Object,
          default:{}
        }
      },
      computed:{
          currentIndex(){
            this.scrollY=this.scrollY+0
            for (let i = 0; i <this.listHeight.length-1 ; i++) {
              let one = this.listHeight[i]
              let two = this.listHeight[i+1]
              if (one<=this.scrollY && two>this.scrollY){
                return i
              }
            }
            return 0
          }
      },
      methods: {
        _initScroll(){
          this.menuScroll = new BScroll(this.$refs.menuWrapper,{click:true});
          this.foodScroll = new BScroll(this.$refs.foodsWrapper,{click:true,probeType:3});
          this.foodScroll.on('scroll',(pos)=>{
            this.scrollY =Math.abs(Math.round(pos.y))
          })
          this._accumulateHeight()
        },
        selectmenu(index){
          let listmenu = this.$refs.foodsWrapper.getElementsByClassName('foods-list-hook')
          let el = listmenu[index]
          this.foodScroll.scrollToElement(el,300)
          this.currentIndex = index
        },
        _accumulateHeight(){
          let listmenu = this.$refs.foodsWrapper.getElementsByClassName('foods-list-hook')
          let height =0
          this.listHeight.push(height)
          for (let i = 0; i <listmenu.length ; i++) {
            height += listmenu[i].clientHeight
            this.listHeight.push(height)
          }
        }
      },
      created(){
          this.typeMap=['decrease','discount','special','invoice','guarantee']
          this.$axios.get('/api/goods').then((data)=>{
            this.goods = data.data.data
            this.$nextTick(()=>{
              this._initScroll()
            })
          })



      }
    }
</script>

<style lang="stylus" scoped>
  @import "../../common/stylus/index.styl"
  @import "../../common/stylus/background.styl"
  .goods
    position absolute
    display flex
    top 174px
    right 0
    bottom 48px
    width 100%;
    .menu-wrapper
      flex 0 0 80px
      background-color #f3f5f7
      color #f3f5f7
      overflow hidden
      .menu
        .menu-item
          display table
          font-size 12px
          width 80px
          height 54px
          line-height 14px
          color rgb(77,85,93)
          font-weight 200
          padding 0 12px
          box-sizing border-box
          border-1px(rgba(7,17,27,0.1))
          &:last-child
            border-no()
          &.current
            background-color rgb(255,255,255)
          .text
            display table-cell
            vertical-align middle
            .icon
              display inline-block
              width 12px
              height 12px
              background-size 12px 12px
              &.decrease
                bg-img('decrease_1')
              &.discount
                bg-img('discount_1')
              &.special
                bg-img('special_1')
              &.invoice
                bg-img('invoice_1')
              &.guarantee
                bg-img('guarantee_1')
    .foods-wrapper
      flex 1
      overflow hidden
      .title
        width 100%
        font-size 12px
        line-height 26px
        padding-left 14px
        color rgb(147,153,159)
        background-color #f3f9f7
        box-sizing border-box
        border-left 3px solid #d9dde1
      .foods
        .food
          .content-wrapper
            padding 18px
            box-sizing border-box
            display flex
            border-1px(rgba(7,17,27,0.1))
            .avatar
              flex 0 0 57px
              height 57px
            .describe
              flex 1
              margin-left 10px
              margin-top 2px
              .name
                font-size 14px
                color rgb(7,17,27)
                line-height 14px
              .description
                font-size 10px
                color rgb(147,153,159)
                line-height 10px
                margin-top 8px
              .count-rating
                font-size 10px
                color rgb(147,153,159)
                line-height 10px
                margin-top 8px
                .sellCount
                  margin-right 12px
              .price
                font-size 14px
                font-weight 700
                line-height 24px
                color rgb(147,153,159)
                .new
                  font-size 14px
                  font-weight 700
                  line-height 24px
                  color rgb(240,20,20)
                  margin-right 8px
                .old
                  font-size 10px
                  color rgb(147,153,159)
                  line-height 24px
                  font-weight 700
                  text-decoration line-through
</style>
