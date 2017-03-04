# encoding:utf-8
import time
from collections import deque,OrderedDict,defaultdict
from collections import _heapq as heapq
import bisect
import itertools as it
'''主要演示python自带的高效的数据结构'''

# deque 为效率较高的双向队列，可以从头部或是尾部进行插入删除操作，而且都是消耗的常数级时间，list虽然也可以实现类似的功能，但是使用list从头部进行插入删除操作时
# 消耗的就是线性级别的时间
def deque_sample():
    items=range(100000)
    # 始终在list的尾部进行操作
    def process_by_list_tail(items):
        start=time.time()
        l=[]
        for i in items:
            l.append(i)
        for i in range(len(l)):
            l.pop()
        print 'list tail process elasted:%s' % str(time.time()-start)
    # 始终在list的头部进行操作,如果数据量较大，消耗的时间呈现线性增长
    def process_by_list_head(items):
        start=time.time()
        l=[]
        for i in items:
            l.insert(0,i)
        for i in range(len(l)):
            l.pop(0)
        print 'list head process elasted:%s' % str(time.time()-start)
    # 始终在deque的尾部进行操作
    def process_by_deque_tail(items):
        start=time.time()
        dq=deque()
        for i in items:
            dq.append(i)
        for i in range(len(items)):
            dq.pop
        print 'deque tail process elasted:%s' % str(time.time()-start)
    # 始终在deque的头部进行操作
    def process_by_deque_head(items):
        start=time.time()
        dq=deque()
        for i in items:
            dq.appendleft(i)
        for i in range(len(items)):
            dq.popleft()
        print 'deque head process elasted:%s' % str(time.time()-start)

    process_by_list_tail(items)
    process_by_list_head(items)
    process_by_deque_tail(items)
    process_by_deque_head(items)

# 有序字典
def ordered_dict():
    # 使用有序字典,key的顺序按照存放的顺序存储
    od1=OrderedDict()
    od2=OrderedDict()
    od1['foo']=1
    od1['bar']=2
    od2['foo']='red'
    od2['bar']='yellow'
    od1['zoo']=3
    od1['dark']=4
    od2['zoo']='blue'
    od2['dark']='white'
    print 'Output By ordered dict:'
    for v1,v2 in zip(od1.values(),od2.values()):
        print v1,v2
    print '\n'
    print 'Output By normal dict:'
    # 使用无序字典，key的顺序按照hash规则进行存储
    od3={}
    od4={}
    od3['foo']=1
    od3['bar']=2
    od3['zoo']=3
    od3['dark']=4
    od4['foo']='red'
    od4['bar']='yellow'
    od4['zoo']='blue'
    od4['dark']='white'
    for v1,v2 in zip(od3.values(),od4.values()):
        print v1,v2

# 使用带有默认值的dict

# 使用带有默认值的dict
def defaultvalue_dict():
    # 默认值为0（int）
    dt=defaultdict(int)
    print dt['age']

# 通过heapq实现带有优先级功能的queue,该个性消耗的资源与queue中元素的数量是线性级关系
def heapq_sample():
    heap=[]
    original_items=[1,6,3,5,2,7,4,2,5,6,3,2]
    for i in original_items:
        heapq.heappush(heap,i)
    # 按照从小到达的顺序进行pop
    for _ in original_items:
        print heapq.heappop(heap)
    
# 通过bisect实现对有序列表的快速二分查找，消耗的时间是对数级别，如果使用list的index方法进行搜索，消耗的时间是线性级别
def binary_search():
    # 包含一千万个元素的list
    items=list(range(10**8))
    s1=time.time()
    # index 查找
    index_1=items.index(5123425)
    print 'list.index elasted:%s' %(str(time.time()-s1))

    # 使用二分查找　　　　　　　
    s2=time.time()
    index_2=bisect.bisect(items,5123425)
    print 'bisect elasted:%s' %(str(time.time()-s2))


# 演示一些itertools中常用的功能
def itertools_sample():
    i1=range(5)
    i2=range(10,20)
    i3=range(3)
    # 链接两个迭代器
    print list(it.chain(i1,i2))    
    print '\n'
    
    # 重复构造某个迭代器
    #for i in it.cycle(i1):
    #   print i
    #   time.sleep(0.5)   

    print '\n'
    # 笛卡尔积
    print list(it.product(i1,i2))

    print '\n'
    # 有序组合:顺序不同，就代表不同的结果
    print list(it.permutations(i3))

    print '\n'
    # 无序组合:不关心顺序，只关心元素是否重复
    print list(it.combinations(i1,3))

if __name__ ==  '__main__':
    #deque_sample()
    #ordered_dict()
    #defaultvalue_dict()
    #heapq_sample()
    #binary_search()
    itertools_sample()




