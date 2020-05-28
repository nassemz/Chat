package messages;

public interface ServerMessages {
    public  static final String GRANT_ACCESS       	       = "#-GRANT_ACCESS-#";
    public  static final String USER_NOT_EXIST     	       = "#-USER_NOT_EXIST-#";
    public  static final String WRONG_PASSWORD                 = "#-WRONG_PASSWORD-#";
    public  static final String USER_ALREADY_EXIST 	       = "#-USER_ALREADY_EXIST-#";
    public  static final String USER_IS_ADDED                  = "#-USER_IS_ADDED-#";
    public  static final String USER_IS_DELETED    	       = "#-USER_IS_DELETED-#";
    public  static final String USER_NOT_VALID                 = "#-USER_NOT_VALID-#";
    public  static final String USER_FAIL_REMOVED              = "#-USER_FAIL_REMOVED-#";
    public  static final String ADMIN_NOT_EXIST                = "#-ADMIN_NOT_EXIST-#";
    public  static final String CANCEL_CONNECTION              = "#-CANCEL_CONNECTION-#";
    
    public  static final String ADD_USER                         = "#-ADD-USER-#";
    public  static final String DELETE_USER                      = "#-DELETE-USER-#";
    public  static final String CHECK_USER                       = "#-CHECK-USER-#";
    public  static final String CHECK_PASSWORD                   = "#-CHECK-PASSWORD-#";
    
    public  static final String  LIST_OF_USER                   = "#-LIST_OF_USER-#";
    public  static final String END_OF_USERS                    = "#-END_OF_USERS-#";
    
    public  static final int DEFUALTPORT                         = 55554;
}
