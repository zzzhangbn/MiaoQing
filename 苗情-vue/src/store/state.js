export default {
    get UserToken() {
        return localStorage.getItem('token')
    },
    set UserToken(value) {
        localStorage.setItem('token', value)
    },
    /**用户登录时的选择的作物类别 */
    cropCategory:sessionStorage.getItem('cropCategory')?sessionStorage.getItem('cropCategory'):'',
    // 用户登录数据
    userData:sessionStorage.getItem('userData')?sessionStorage.getItem('userData'):'',

}
