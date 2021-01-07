
//交集
export function intersection(a, b) {
    //返回能在b中找到的a的值
    return a.filter(v => b.indexOf(v) > -1)
}

//差集
export function subtraction(a, b) {
    // 返回在b中找不到的
    a.filter(v => b.indexOf(v) == -1)
}

//补集
//某个集合是另一个集合的子集
export function complementary(a, b) {
    return a.filter(v => !(b.indexOf(v) > -1))
        .concat(b.filter(v => !(a.indexOf(v) > -1)))
}

//并集
export function union(a, b) {
    // 1.找到b对a的差集
    // 2.粘贴给a
    a.concat(b.filter(v => a.indexOf(v) == -1));
}