<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body style="color: #666; font-size: 14px; font-family: 'Open Sans',Helvetica,Arial,sans-serif;">
<div class="box-content" style="width: 80%; margin: 20px auto; max-width: 800px; min-width: 600px;">
    <!-- <div class="header-tip" style="font-size: 12px;
                                    color: #aaa;
                                    text-align: right;
                                    padding-right: 25px;
                                    padding-bottom: 10px;">
         Confidential - Scale Alarm Use Only
     </div>-->
    <div class="info-top" style="padding: 15px 25px;
                                 border-top-left-radius: 10px;
                                 border-top-right-radius: 10px;
                                 background: #D9FFBD;
                                 color: #fff;
                                 overflow: hidden;
                                 line-height: 32px;">
        <!-- <img src="cid:icon-alarm" style="float: left; margin: 0 10px 0 0; width: 32px;" />-->
        <div style="color:#010e07"><strong>接口自动化测试报告</strong></div>
    </div>
    <div class="info-wrap" style="border-bottom-left-radius: 10px;
                                  border-bottom-right-radius: 10px;
                                  border:1px solid #ddd;
                                  overflow: hidden;
                                  padding: 15px 15px 20px;">
        <div class="tips" style="padding:15px;">
            <p style=" list-style: 160%; margin: 10px 0;">Hi all,</p>
            <p style=" list-style: 160%; margin: 10px 0;">以下是本次接口自动化测试结果统计, 敬请查看.<br>For more please see attachment. </p>
        </div>
        <div class="time" style="text-align: right; color: #999; padding: 0 15px 15px;">${date}</div>
        <br>
        <table class="list" style="width: 100%; border-collapse: collapse; border-top:1px solid #eee; font-size:12px;">
            <thead>
            <tr style=" background: #fafafa; color: #333; border-bottom: 1px solid #eee;">
                <td>总数(sum)</td><td>通过数(passed)</td><td>失败数(failed)</td><td>跳过数(Skip)</td>
            </tr>
            </thead>
            <tbody>
            <tr><td>${sum}</td><td>${passed}</td><td>${failed}</td><td>${Skip}</td>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>