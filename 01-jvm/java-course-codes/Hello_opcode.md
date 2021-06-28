# Hello Class Bytecode 分析 

## main 方法 Opcode 對應

Index | Bytecode | Mnemonics
------|:--------:|---------:|
0   | iconst_1      | 04
1   | istore_1      | 3c
2   | ldc2_w        | 14
3   |               | 00
4   |               | 02
5   | dstore_2      | 49
6   | ldc2_w        | 14
7   |               | 00
8   |               | 04
9   | lstore        | 37
10  |               | 04
11  | iconst_4      | 07
12  | istore        | 36
13  |               | 06
14  | bipush        | 10
15  |               | 55
16  | istore        | 36
17  |               | 07
18  | ldc           | 12
19  |               | 06
20  | invokevirtual | b6
21  |               | 00
22  |               | 07
23  | bipush        | 10
24  |               | 0a
25  | if_icmpge     | a2
26  |               | 00
27  |               | 21
28  | getstatic     | b2
29  |               | 00
30  |               | 08
31  | new           | bb
32  |               | 00
33  |               | 09
34  | dup           | 59
35  | invokespecial | b7
36  |               | 00
37  |               | 0a
38  | ldc           | 12
39  |               | 0b
40  | invokevirtual | b6
41  |               | 00
42  |               | 0c
43  | dload_2       | 28
44  | invokevirtual | b6
45  |               | 00
46  |               | 0d
47  | lload         | 16
48  |               | 04
49  | invokevirtual | b6
50  |               | 00
51  |               | 0e
52  | invokevirtual | b6
53  |               | 00
54  |               | 0f
55  | invokevirtual | b6
56  |               | 00
57  |               | 10
58  | iload         | 15
59  |               | 07
60  | iload_1       | 1b
61  | if_icmple     | a4
62  |               | 00
63  |               | 0b
64  | getstatic     | b2
65  |               | 00
66  |               | 08
67  | ldc           | 12
68  |               | 11
69  | invokevirtual | b6
70  |               | 00
71  |               | 10
72  | iconst_0      | 03
73  | istore        | 36
74  |               | 08
75  | iload         | 15
76  |               | 08
77  | iload_1       | 1b
78  | if_icmpge     | a2
79  |               | 00
80  |               | 25
81  | getstatic     | b2
82  |               | 00
83  |               | 08
84  | new           | bb
85  |               | 00
86  |               | 09
87  | dup           | 59
88  | invokespecial | b7
89  |               | 00
90  |               | 0a
91  | ldc           | 12
92  |               | 12
93  | invokevirtual | b6
94  |               | 00
95  |               | 0c
96  | iload_1       | 1b
97  | iload         | 15
98  |               | 06
99  | imul          | 68
100 | invokevirtual | b6
101 |               | 00
102 |               | 13
103 | invokevirtual | b6
104 |               | 00
105 |               | 0f
106 | invokevirtual | b6
107 |               | 00
108 |               | 10
109 | iinc          | 84
110 |               | 08
111 |               | 01
112 | goto          | a7
113 |               | ff
114 |               | db
115 | return        | b1
