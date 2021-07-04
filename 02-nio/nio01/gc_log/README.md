## 分析
- 分析 GC Log 主要關注兩個數據：GC 暫停時間與 GC 之後的 Memory 使用量/使用率
- Young 區，可以關注暫停時間，以及 GC 後的 Memory 使用率是否正常
- Full GC 更關注 Old 區使用量有沒有下降以及下降多少，如果 Full GC 後 Memory 使用率還是很高，系統可能有問題

## 參數設定
- -Xmx -Xms 同步調升，GC 頻率減少，但是每次 GC 處理時間增加
- 沒有配置 -Xms GC 頻率增加

## Serial GC
- 使用 mark-sweep-compact 清理 Old 區空間
- 由於清理 Old 區採用單一 thread，Old 區佔用空間越大，GC 所耗費時間就越多，對系統效能有顯著影響
- 一般情況很少採用 Serial GC

## Parallel GC
- Java 8 預設 GC
- 使用 mark-copy 清理 Young 區
- 使用 mark-sweep-compact 清理 Old 區
- 採用多個 thread 併發執行，GC 執行時間大幅減少
- 適用多核心系統，以增加系統吞吐量
- 為達增加系統吞吐量，會盡可能多使用 CPU 資源
- 清理 Old 區可能發生較長時間卡頓

## CMS GC
- CMS 設計目標是針對 Old 區避免出現長時間卡頓的狀況
- 使用 mark-copy 清理 Young 區
- 併發 Old 區清理階段：
- Initial Mark: 標記 GC Root
- Concurrent Mark: 遍歷 Old 區並標記所有存活的 Object
- Concurrent Preclean: 統計前面併發標記執行過程發生改變的 Object
- Concurrent Abortable Preclean: 嘗試在 Final Remark 前多做點前置作業
- Final Remark: 完成所有 Old 區存活 Object 標記
- Concurrent Sweep: 刪除不再使用的 Object 並回收 Memory 空間
- Concurrent Reset: 重置 CMS 演算法相關的資料結構，下次觸發 GC 可以直接使用
- Old 區可能存在 Memory 碎片問題，在某些情況下 GC 會有不可預測的暫停時間，特別是 Heap 區較大情況

## G1 GC
- Java 9 之後的預設 GC
- Evacuation Pause: (young): 新生代滿了之後，其中的存活 Object 被複製到存活區
- Concurrent Marking: Heap 區總體使用率達到一定值(預設是 45%)，就會觸發標記，執行階段如下：
  - Initial Mark
  - Root Region Scan: 標記從 Root 區可達的存活 Object
  - Concurrent Mark
  - Remark
  - Cleanup
- Evacuation Pause (mixed): 併發標記完後，G1 將執行一次 mixed collection，不只清理新生代，還將一部分老年代也加入到 collection set
