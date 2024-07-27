def minOperationsToEmptyString(s):
    n = len(s)
    count = 0
    i = 0
    j = n - 1

    while i <= j:
        if s[i] == s[j]:
            i += 1
            j -= 1
        else:
            # 找到最长的回文子串
            start = i
            end = j
            while start < end and s[start] == s[end]:
                start += 1
                end -= 1
            # 删除回文子串并更新操作次数
            if start >= end:
                count += 1
                break
            else:
                count += 2
                i = start
                j = end
    return count


# 读取输入
n = int(input())
s = input()

# 计算最少需要多少次操作
result = minOperationsToEmptyString(s)
print(result)