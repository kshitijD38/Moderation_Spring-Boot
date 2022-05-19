There three apis

# 1.  login
    a.  there are 3 users currently
        i)  userName: author1 and password: auth@123
        ii) userName: author2 and password: auth@123
        iii) userName: moderator and password: moderator@123
    b.  with userName and password as request body you can see list of flgged posts, rejected posts and approved posts for all users, for moderator you can see only flagged posts of all the users.
# 2.  send post
    a.  you can send post with userName and post as body depending on
        bad words it maybe flagged
# 3.  approve-or-reject
    a.  this api is for moderator to approve or reject
    b.  send post_id(you can get from list of flagged posts) and new
        status as body
