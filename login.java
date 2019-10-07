public class login {
    private String login;

    protected void setLogin(String LoginIn) {
        
        login = LoginIn;
    }
    
      LoginPass()
      {
          login = "";
      }
    
      LoginPass(String LoginIn) {
          this();
          setLogin(LoginIn);
      }
    
      public String YourLogin() {
          return login;
      }
    
    }