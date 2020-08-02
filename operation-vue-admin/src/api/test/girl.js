import request from '@/utils/request'
export default {
    getList:function(params) {
        return request({
            url: '/test/girl/list',
            method: 'get',
            params
        })
    },
    add:function(params) {
        return request({
            url: '/test/girl',
            method: 'post',
            params
        })
    },
    update:function(params) {
        return request({
            url: '/test/girl',
            method: 'PUT',
            params
        })
    },
    remove:function(id) {
        return request({
            url: '/test/girl',
            method: 'delete',
            params: {
                id: id
            }
        })
    }
}
