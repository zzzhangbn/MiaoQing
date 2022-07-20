export default {
    LOGIN_IN(state, token) {
        state.UserToken = token
    },
    LOGIN_OUT(state) {
        state.UserToken = ''
    },
    toggleNavCollapse(state) {
        state.isSidebarNavCollapse = !state.isSidebarNavCollapse
    },
    setCrumbList(state, list) {
        state.crumbList = list
    },
    /*用户登陆时选择的类别*/
    setCropCategory(state,cropCategory){
        state.cropCategory = cropCategory;
        sessionStorage.setItem('cropCategory',JSON.stringify(cropCategory));
    },
    //用户登录数据
    setUserData(state,userData){
        state.userData = userData;
        sessionStorage.setItem('userData',userData);
    },
}
