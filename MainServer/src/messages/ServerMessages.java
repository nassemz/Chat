package messages;

public interface ServerMessages {
    public  static final String GRANT_ACCESS       	        = "#-GRANT_ACCESS-#";
    public  static final String USER_NOT_EXIST     	        = "#-USER_NOT_EXIST-#";
    public  static final String WRONG_PASSWORD                  = "#-WRONG_PASSWORD-#";
    public  static final String USER_ALREADY_EXIST 	        = "#-USER_ALREADY_EXIST-#";
    public  static final String USER_IS_ADDED                   = "#-USER_IS_ADDED-#";
    public  static final String USER_IS_DELETED    	        = "#-USER_IS_DELETED-#";
    public  static final String USER_NOT_VALID                  = "#-USER_NOT_VALID-#";
    public  static final String USER_FAIL_REMOVED               = "#-USER_FAIL_REMOVED-#";
    public  static final String ADMIN_NOT_EXIST                 = "#-ADMIN_NOT_EXIST-#";
    
    public  static final String ADD_ONE_USER                    = "#-ADD-ONE-USER-#";
    public  static final String DELETE_ONE_USER                 = "#-DELETE-ONE-USER-#";
    public  static final String ONLINEUSERS                     = "#-ONLINEUSERS-#";
    public  static final String END_ONLINEUSERS                 = "#-END-ONLINEUSERS-#";
    public  static final String OFFLINEUSERS                    = "#-OFFLINEUSERS-#";
    
    public  static final String SEND_FILE                       = "#-SEND-FILE-#";
    public  static final String ADD_USER                        = "#-ADD-USER-#";
    public  static final String DELETE_USER                     = "#-DELETE-USER-#";
    public  static final String CHECK_USER                      = "#-CHECK-USER-#";
    public  static final String CANCEL_CONNECTION               = "#-CANCEL_CONNECTION-#";
    public  static final String CHECK_PASSWORD                  = "#-CHECK-PASSWORD-#";
    public  static final  String DEFAULTSLPITER                 = "#-NEW-LINE-#";
    public  static final int DEFULTPORT                         = 55555;
    public  static final int DEFUALTPORT_DB                     = 55554;
    public  static final  String DEFAULTSERVERDB                = "nissimzo.myvnc.com";
    public  static final int DefultMaxIndex                     = 29;
    
    public  static final String SUCCESS                         = "#-SUCCESS-#";
    public  static final String LOGED_ON                        = "#-LOGED-ON-#";
    
    public  static final String ADMINNAME                       = "Admin";
    public  static final String ADMINPASSWORD                   = "admin";
    public  static final  String GENERAL_CHANNEL                = "#-GENERAL-CHANNEL-#";
    public  static final  String DEFAULTEMPTY                   = "#-EMPTY-#";
    public  static final String CONNECTION_FAIL                 = "#-Connection-Fail-#";
    
    public  static final String LIST_OF_USER                    = "#-LIST_OF_USER-#";
    public  static final String END_OF_USERS                    = "#-END_OF_USERS-#";
    
}
