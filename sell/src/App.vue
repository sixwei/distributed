<template>
  <div id="app">
     <div class="tou border-1px">
       <v-header :seller="seller"></v-header>
     </div>
     <div class="tab">
       <div class="tab-item">
         <router-link to="/good">商品</router-link>
       </div>
       <div class="tab-item">
         <router-link to="/rating">评价</router-link>
       </div>
       <div class="tab-item">
         <router-link to="/seller">卖家</router-link>
       </div>
     </div>
    <router-view :seller="seller"></router-view>

  </div>
</template>

<script>
import header from './components/header/header'
export default {
  name: 'App',
  data(){
    return{
      seller:{}
    }
  },
  components:{
   'v-header': header
  },
  created(){
    this.$axios.get('/api/seller').then((data)=>{
      console.log(data.data.data);
      this.seller = data.data.data
    })
  }
}
</script>

<style lang="stylus">
  @import "./common/stylus/background.styl"
  .tab
    display flex
    width 100%
    height 40px
    border-1px(rgba(7,17,27,0.1))
    .tab-item
      line-height 40px
      text-align center
      flex 1
      &>a
        display inline-block
        font-size 14px
        color rgb(77,85,93)
        line-height 14px
        width 100%
        &.router-link-active
          color rgb(240,20,20)
          font-size 14px
          line-height 14px
  .tou
    width 100%
    height 134px

</style>
