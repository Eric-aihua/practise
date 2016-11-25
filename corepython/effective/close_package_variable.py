# encoding:utf-8
__author__ = 'eric.sun'
'''演示闭包函数如何对外层的变量进行赋值'''


def sort_priority(numbers, group):
    """
    对numbers数组进行排序，但是group中的元素会有较高的优先级
    :param number:
    :param group:
    """

    def helper(item):
        if item in group:
            # 在group中的元素有较高的优先级
            return 0, item
        return 1, item

    numbers.sort(key=helper)


def sort_priority_has_return(numbers, group):
    """
    相对于sort_priority，增加了一个use_priority_group，表示是否使用了group
    :param number:
    :param group:
    """
    use_priority_group = False

    def helper(item):
        if item in group:
            # 在赋值的时候，闭包作用域中赋值变量时，发现该域中没有，就会新建该对象，所以外层的use_priority_group 不会被赋值
            use_priority_group = True
            return 0, item
        return 1, item

    numbers.sort(key=helper)
    return use_priority_group


def sort_priority_has_return_bylist(numbers, group):
    """
    对于python3 可以使用nonlocal来解决该问题，如果是python2则可以通过可变量的技巧来操作，例如list,map
    本例用list来解决该问题
    :param number:
    :param group:
    """
    use_priority_group = [False]

    def helper(item):
        if item in group:
            use_priority_group[0] = True
            return 0, item
        return 1, item

    numbers.sort(key=helper)
    return use_priority_group[0]


if __name__ == '__main__':
    numbers = [1, 3, 4, 2, 4, 53, 2, 45, 2, 2]
    # 4和53具有较高的优先级
    priority_group = [4, 53]
    # sort_priority(numbers,priority_group)
    # print sort_priority_has_return(numbers, priority_group)
    print sort_priority_has_return_bylist(numbers, priority_group)
    print numbers