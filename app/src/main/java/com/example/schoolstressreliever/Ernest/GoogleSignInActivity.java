//package com.example.schoolstressreliever.academic;
//import androidx.appcompat.app.AppCompatActivity;
//
//
////import com.google.android.gms.auth.api.signin.GoogleSignIn;
////import com.google.android.gms.auth.api.signin.GoogleSignInClient;
//
//
//public class GoogleSignInActivity extends AppCompatActivity {
//
//
//
//    /*
//    private GoogleSignInClient mGoogleSignInClient;
//    private final static int RC_SIGN_IN = 123;
//    private FirebaseAuth mAuth;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_sign_up_ascreen);
//
//        mAuth = FirebaseAuth.getInstance();
//        createRequest();
//    }
//
//    public void bruh(View v){
//        signInFo();
//    }
//
//    private void createRequest() {
//        // Configure Google Sign In
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//        // Build a GoogleSignInClient with the options specified by gso.
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//    }
//
//    private void signInFo() {
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                // Google Sign In was successful, authenticate with Firebase
//                GoogleSignInAccount account = task.getResult(ApiException.class);
//                firebaseAuthWithGoogle(account);
//            } catch (ApiException e) {
//                // Google Sign In failed, update UI appropriately
//                // ...
//                Intent intent = new Intent(getApplicationContext(),UserProfileActivity.class);
//                startActivity(intent);
//                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
//
//        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            System.out.println("aden");
//                            // Sign in success, update UI with the signed-in user's information
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            if (user != null) {
//                                Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
//                                startActivity(intent);
//                            }
//
//                        } else {
//                            Toast.makeText(SignUpAScreen.this, "Sorry auth failed.", Toast.LENGTH_SHORT).show();
//
//                        }
//
//                        // ...
//                    }
//                });
//    }
//    /*
//
//     */
//}
//
//
//
