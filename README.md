单体web项目

#生成基础的CRUD代码
在命令行中执行 datasourceCodeGenerate.bat 文件  
其他系统可以把文件的内容放到命令行执行  
在pom.xml文件中配置数据库连接信息

集成了 redis,redis初始化，lambda表达式的学习

git 命令：
git add 文件;
git commit -m "说明";
git push;推送远程库
revert命令 revert 之后你的本地代码会回滚到指定的历史版本,这时你再 git push 既可以把线上的代码更新。
git log;
git revert commit_id;
git push;
修改完之后按esc键退出编辑状态，再按大写ZZ就可以保存退出vim编辑器。vim操作符中说的 qw 可以保存并退出 根本没用 

reset命令：直接删除上次提交，使用reset命令
git reset --hard HEAD^;
git push origin master -f
HEAD是指向最新的提交，上一次提交是HEAD^,上上次是HEAD^^,也可以写成HEAD～2 ,依次类推。

删除上次提价还可以使用revert命令
git revert HEAD
git push origin master

注解使用，多线程，线程池的应用

