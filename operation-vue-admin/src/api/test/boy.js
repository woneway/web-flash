import request from '@/utils/request'
export default {
    getList:function(params) {
        return request({
            url: '/test/boy/list',
            method: 'get',
            params
        })
    },
    add:function(params) {
        return request({
            url: '/test/boy',
            method: 'post',
            params
        })
    },
    update:function(params) {
        return request({
            url: '/test/boy',
            method: 'PUT',
            params
        })
    },
    remove:function(id) {
        return request({
            url: '/test/boy',
            method: 'delete',
            params: {
                id: id
            }
        })
    }
}
