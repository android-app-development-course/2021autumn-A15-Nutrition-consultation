# 项目文档

## 主要功能

- [x] 可以直接通过关键字模糊搜索某种食物的营养成分
- [x] 可以在分类页面查看每个分类下每个具体食物的营养成分
- [x] 可以把已查询到的食物添加进分析列表
- [x] 可以对分析列表中的食物进行选择和输入具体食用量
- [x] 可以根据当前时间结合营养需求给出建议
- [x] 每个fragment都有不一样的颜色，会自动改变，侧边栏的部分也会随之改变

## 技术难点

1. 分类详情获取

   调用`addOnScrollListener`判断滑到顶部还是底部，然后获取上一页或下一页的数据就好

   ```kotlin
   binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
       override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
           super.onScrolled(recyclerView, dx, dy)
   
           //Log.i("page_test", "--------------------------------------")
           if (binding.recyclerView.canScrollVertically(1)) {
               //Log.i(TAG, "direction 1: true")
           } else {
               Log.i("page_test", "direction 1: false") //滑动到底部
               page_num++
               Log.i("page_test","page = $page_num")
               detailViewModel.getFoodList(foodCategoryName, page_num)
               jumptofirst()
           }
           if (binding.recyclerView.canScrollVertically(-1)) {
               //Log.i(TAG, "direction -1: true")
           } else {
               Log.i("page_test", "direction -1: false") //滑动到顶部
               page_num--
               Log.i("page_test","page = $page_num")
               detailViewModel.getFoodList(foodCategoryName, page_num)
               jumptofirst()
           }
       }
   })
   ```

2. 分析列表的暂时存储和修改

   把数据存进viewmodel里，只要绑定的是activity就能不随着fragment的生命周期变化

   ```kotlin
   selectViewModel =
       ViewModelProvider(activity!!).get(SelectViewModel::class.java)
   ```

3. 界面的颜色随fragment变化而改变

   通过navController获取此时的fragment的ID，然后改变就行，这个侧边栏的headerlayout的改变才是难点，要先获取它的id，getHeaderView(0)指的是第一个headerlayout

   ```kotlin
   fun appbarcolor(navController:NavController){//用于改变actionbar和statusbar颜色
       val header = binding.navView.getHeaderView(0).findViewById<View>(R.id.draw_header)
   
       navController.addOnDestinationChangedListener { _, destination, _ -> when(destination.id){
           R.id.nav_home -> {binding.appBarMain.toolbar.setBackgroundColor(Color.parseColor("#00c48c"))
               header.setBackgroundColor(Color.parseColor("#00c48c"))
               window.statusBarColor = Color.parseColor("#00c48c")
   
           }
           R.id.nav_search -> {binding.appBarMain.toolbar.setBackgroundColor(Color.parseColor("#0084f4"))
               header.setBackgroundColor(Color.parseColor("#0084f4"))
                   window.statusBarColor = Color.parseColor("#0084f4")}
           R.id.nav_select -> {binding.appBarMain.toolbar.setBackgroundColor(Color.parseColor("#EB8C8F"))
               header.setBackgroundColor(Color.parseColor("#EB8C8F"))
                   window.statusBarColor = Color.parseColor("#EB8C8F")}
           R.id.nav_tag -> {binding.appBarMain.toolbar.setBackgroundColor(Color.parseColor("#ECCE5F"))
               header.setBackgroundColor(Color.parseColor("#ECCE5F"))
                   window.statusBarColor = Color.parseColor("#ECCE5F")}
           R.id.nav_about -> {binding.appBarMain.toolbar.setBackgroundColor(Color.parseColor("#ffa26b"))
               header.setBackgroundColor(Color.parseColor("#ffa26b"))
                   window.statusBarColor = Color.parseColor("#ffa26b")
           }
       }
   
       }
   }
   ```

4. 打开侧边栏后状态栏的颜色问题

   监控侧边栏滑动程度就好了

   ```kotlin
   drawerLayout.addDrawerListener(object : DrawerListener {
       override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
           //抽屉正在滑动时调用
           if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
               //部分可见就会进入
               window.statusBarColor = Color.TRANSPARENT
           }
           else{
               appbarcolor(navController)
           }
       }
   
       override fun onDrawerOpened(drawerView: View) {
           //抽屉完全打开后调用
   
   
       }
   
       override fun onDrawerClosed(drawerView: View) {
   
       }
   
       override fun onDrawerStateChanged(newState: Int) {}
   })
   ```

5. appbar的跳转问题

   直接把appbar里面的item改成和drawer里的id一样就能用navigation组件之间导航了

   ```kotlin
   override fun onOptionsItemSelected(item: MenuItem): Boolean {
   
       return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment_content_main))
               || super.onOptionsItemSelected(item)
   }
   ```

6. 在reycleview中跳转fragment

   要在`onBindViewHolder`调用`findNavController().navigate()`

7. 传递自定义类

   通过bundle传递，利用navigation组件，调用`bundle.putParcelable`，但要先让类实现`Parcelable`接口

   ```kotlin
   @Parcelize
   data class Food(val name:String="",
                   var isSelected:Boolean=true,
                   var intakeAmount:Int = 100,
                   @SerializedName("rl") var power:Float=0.0F,
                   @SerializedName("dbz") var protein:Float=0.0F,
                   @SerializedName("zf") var fat:Float=0.0F,
                   @SerializedName("shhf") var carbohydrate:Float=0.0F,
                   @SerializedName("ssxw") var diaryFiber:Float=0.0F):Parcelable {
       override fun equals(other: Any?): Boolean {
           if (other != null) {
               return if (other::class.java == Food::class.java) {
                   val food = other as Food
                   (food.name == name && food.protein == protein && food.fat == fat
                           && food.carbohydrate == carbohydrate && food.diaryFiber == diaryFiber)
               } else {
                   false;
               }
           }
           return false
       }
   
       override fun hashCode(): Int {
           var result = name.hashCode()
           result = 31 * result + power.hashCode()
           result = 31 * result + protein.hashCode()
           result = 31 * result + fat.hashCode()
           result = 31 * result + carbohydrate.hashCode()
           result = 31 * result + diaryFiber.hashCode()
           return result
       }
   }
   ```

   





