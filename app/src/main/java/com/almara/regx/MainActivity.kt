package com.almara.regx

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.ObjectAnimator
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnticipateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds


class MainActivity : AppCompatActivity() {

    private lateinit var ivClose: ImageView
    private lateinit var ivMore: ImageView
    private lateinit var ivResult:ImageView
    //private lateinit var ivShare:ImageView
    private lateinit var etRegex:EditText
    private lateinit var etTest:EditText
    private lateinit var tvAppVersion: TextView
    private lateinit var tvLogo: TextView
    private lateinit var tvLogo2: TextView
    private lateinit var tvMail: TextView

    private lateinit var llMainContent:LinearLayout
    private lateinit var llTopContent:LinearLayout
    private lateinit var llLogo:LinearLayout
    private lateinit var clLogo:ConstraintLayout

    private var mScreeHeight: Float = 0f
    private var mScreeWidth: Float = 0f

    private val TIME_5000: Long = 5000
    private val TIME_4500: Long = 4500
    private val TIME_4000: Long = 4000
    private val TIME_3500: Long = 3500
    private val TIME_3000: Long = 3000
    private val TIME_2500: Long = 2500
    private val TIME_2000: Long = 2000
    private val TIME_1500: Long = 1500
    private val TIME_1000: Long = 1000
    private val TIME_500: Long = 500

    private lateinit var mAdView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initListeners()
        initScreenSize()
        fadeOutTvLogos()
        fadeOutLogoIcon()
        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        mAdView.loadAd(AdRequest.Builder().build())

    }

    private fun fadeOutLogoIcon(){
        ivClose.apply {
            alpha = 0f
            visibility = View.VISIBLE
            animate()
                .alpha(1f)
                .setDuration(TIME_3000)
                .setListener(object:AnimatorListener{
                    override fun onAnimationRepeat(animation: Animator?) {
                    }
                    override fun onAnimationEnd(animation: Animator?) {
                        fadeInTvLogos()
                    }
                    override fun onAnimationCancel(animation: Animator?) {
                    }
                    override fun onAnimationStart(animation: Animator?) {
                    }
                }).setInterpolator(AnticipateInterpolator()).start()
        }
    }

    private fun fadeOutTvLogos(){
        tvLogo.apply {
            alpha = 0f
            visibility = View.VISIBLE
            animate()
                .alpha(1f)
                .setDuration(TIME_1000)
                .setListener(object:AnimatorListener{
                    override fun onAnimationRepeat(animation: Animator?) {
                    }
                    override fun onAnimationEnd(animation: Animator?) {
                    }
                    override fun onAnimationCancel(animation: Animator?) {
                    }
                    override fun onAnimationStart(animation: Animator?) {
                    }
                }).setInterpolator(AnticipateInterpolator()).start()
        }
        tvLogo2.apply {
            alpha = 0f
            visibility = View.VISIBLE
            animate()
                .alpha(1f)
                .setDuration(TIME_2000)
                .setListener(object:AnimatorListener{
                    override fun onAnimationRepeat(animation: Animator?) {
                    }
                    override fun onAnimationEnd(animation: Animator?) {
                    }
                    override fun onAnimationCancel(animation: Animator?) {
                    }
                    override fun onAnimationStart(animation: Animator?) {
                    }
                }).setInterpolator(AnticipateInterpolator()).start()
        }
    }

    private fun fadeInTvLogos(){
        tvLogo.apply {
            alpha = 1f
            visibility = View.VISIBLE
            animate()
                .alpha(0f)
                .setDuration(TIME_1000)
                .setListener(object:AnimatorListener{
                    override fun onAnimationRepeat(animation: Animator?) {
                    }
                    override fun onAnimationEnd(animation: Animator?) {
                    }
                    override fun onAnimationCancel(animation: Animator?) {
                    }
                    override fun onAnimationStart(animation: Animator?) {
                    }
                }).setInterpolator(AnticipateInterpolator()).start()
        }
        tvLogo2.apply {
            alpha = 1f
            visibility = View.VISIBLE
            animate()
                .alpha(0f)
                .setDuration(TIME_2000)
                .setListener(object:AnimatorListener{
                    override fun onAnimationRepeat(animation: Animator?) {
                    }
                    override fun onAnimationEnd(animation: Animator?) {
                        hideUselessViews()
                        mooveLogoToTop()
                    }
                    override fun onAnimationCancel(animation: Animator?) {
                    }
                    override fun onAnimationStart(animation: Animator?) {
                    }
                }).setInterpolator(AnticipateInterpolator()).start()
        }
    }

    private fun hideUselessViews(){
        tvAppVersion.visibility = View.GONE
        tvLogo.visibility = View.GONE
        tvLogo2.visibility = View.GONE
        tvMail.visibility = View.GONE
    }

    private fun initListeners(){
        val etListener = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(s.toString() == null || s.toString().isEmpty()){
                    ivResult.visibility = View.INVISIBLE
                }else if(etTest?.text.toString().isNotEmpty() && etRegex?.text.toString().isNotEmpty()){
                    ivResult.visibility = View.VISIBLE
                    if(matches(etRegex?.text.toString(),etTest?.text.toString())){
                        ivResult.setImageDrawable(getDrawable(R.drawable.ic_ok))
                    }else{
                        ivResult.setImageDrawable(getDrawable(R.drawable.ic_error))
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        }
        etRegex.addTextChangedListener(etListener)
        etTest.addTextChangedListener(etListener)
        /*ivShare.setOnClickListener(View.OnClickListener{})*/
    }

    private fun initScreenSize() {
        val display = windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getRealMetrics(outMetrics)
        val density = resources.displayMetrics.density
        mScreeHeight = outMetrics.heightPixels / density
        mScreeWidth = outMetrics.widthPixels / density
    }

    private fun initViews() {
        etRegex = findViewById(R.id.et_regex)
        etTest = findViewById(R.id.et_test)
        ivClose = findViewById(R.id.iv_close)
        ivMore = findViewById(R.id.iv_more)
        ivResult = findViewById(R.id.iv_result)
        //ivShare = findViewById(R.id.iv_share)
        tvLogo = findViewById(R.id.tv_logo)
        tvAppVersion = findViewById(R.id.tv_app_version)
        tvLogo = findViewById(R.id.tv_logo)
        tvLogo2 = findViewById(R.id.tv_logo2)
        tvMail = findViewById(R.id.tv_mail)
        llMainContent = findViewById(R.id.ll_main_content)
        llTopContent = findViewById(R.id.ll_top_content)
        llLogo = findViewById(R.id.ll_logo)
        clLogo = findViewById(R.id.cl_logo)
    }

    private fun matches(regex:String, test:String):Boolean{
        return try{
            regex.toRegex().matches(test)
        }catch (e:Exception){
            ivResult.visibility = View.INVISIBLE
            false
        }
    }

    private fun mooveLogoToTop() {
        //status bar height
        var statusBarHeight = 0
        val resourceId: Int = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusBarHeight = resources.getDimensionPixelSize(resourceId)
        }

        llLogo.visibility = View.VISIBLE

        val location = IntArray(2)
        clLogo.getLocationOnScreen(location)
        //ivClose.getLocationOnScreen(location)
        val animation = ObjectAnimator.ofFloat(clLogo, "translationY", - ((location[1]).toFloat()/3)*2 - (clLogo.height/2) + (statusBarHeight/2))
        //val animation = ObjectAnimator.ofFloat(ivClose, "translationY", - ((location[1]).toFloat()/3)*2 - (ivClose.height/2) + (statusBarHeight/2))
        //val animation = ObjectAnimator.ofFloat(clLogo, "translationY", - location[1].toFloat())
        animation.duration = TIME_1500
        animation.startDelay = TIME_500
        animation.interpolator = DecelerateInterpolator()
        animation.start()
        animation.addListener(object:AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {
            }
            override fun onAnimationEnd(animation: Animator?) {
                showTopViews()
            }
            override fun onAnimationCancel(animation: Animator?) {
            }
            override fun onAnimationStart(animation: Animator?) {
                showMainContentLayout()
            }
        })
    }

    private fun showMainContentLayout(){
        llMainContent.apply {
            alpha = 0f
            visibility = View.VISIBLE
            animate()
                .alpha(1f)
                .setDuration(TIME_1500)
                .setListener(object:AnimatorListener{
                    override fun onAnimationRepeat(animation: Animator?) {
                    }
                    override fun onAnimationEnd(animation: Animator?) {
                    }
                    override fun onAnimationCancel(animation: Animator?) {
                    }
                    override fun onAnimationStart(animation: Animator?) {
                    }
                }).setInterpolator(AccelerateInterpolator()).start()
        }
    }

    private fun showTopViews(){
        ivMore.visibility = View.VISIBLE
        //ivShare.visibility = View.VISIBLE
    }
}