package com.hit.profileapp

import android.content.Intent
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroCustomLayoutFragment
import com.github.appintro.AppIntroPageTransformerType

class AppIntroActivity : AppIntro2() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setImmersiveMode()

        //memasukkan fragment ke slider
        addSlide(
            AppIntroCustomLayoutFragment.newInstance(R.layout.fragment_app_intro1)
        )
        addSlide(
            AppIntroCustomLayoutFragment.newInstance(R.layout.fragment_app_intro2)
        )
        addSlide(
            AppIntroCustomLayoutFragment.newInstance(R.layout.fragment_app_intro3)
        )

        setTransformer(AppIntroPageTransformerType.Depth)
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        var intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onSlideChanged(
        @Nullable oldFragment: Fragment?,
        @Nullable newFragment: Fragment?
    ) {
        super.onSlideChanged(oldFragment, newFragment)
    }
}