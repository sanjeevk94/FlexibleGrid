# FlexibleGrid Publishing Guide

This guide explains how to publish and use the FlexibleGrid library.

## 📦 Publishing Options

### Option 1: JitPack (Recommended - Simplest)

JitPack automatically builds your library from GitHub releases with zero configuration needed on your end.

#### Setup (One-time):
1. Push your code to GitHub
2. Create a GitHub release (tag)
3. Users can immediately use it - no waiting!

#### For Users - How to Install:

**Step 1: Add JitPack repository** (`settings.gradle.kts`):
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }  // ← Add this
    }
}
```

**Step 2: Add dependency** (`build.gradle.kts` or `build.gradle`):
```kotlin
dependencies {
    implementation("com.github.sanjeevk94:FlexibleGrid:v2.0.0")
}
```

Replace `v2.0.0` with the actual release tag.

#### Publisher Workflow:
1. **Make changes** to code
2. **Commit & push** to GitHub
3. **Create a release**:
   ```bash
   git tag -a v2.0.0 -m "Release version 2.0.0"
   git push origin v2.0.0
   ```
4. **Go to GitHub** → Releases → Create Release from tag
5. **Users can now install it!** JitPack auto-builds on first request

---

### Option 2: GitHub Packages

This repository does **not** publish to GitHub Packages by default in the current setup.

If you want that later, add a `maven-publish` configuration to `flexiblegrid/build.gradle.kts`, publish from CI, and document the package coordinates alongside the required token-based repository credentials.

---

### Option 3: Maven Central Repository

Most discoverable but more complex. Requires:
- Sonatype account
- GPG signing setup
- Verification process

*For a future release if needed.*

---

## 🚀 How to Create a Release

### Manual Release:

1. **Commit changes**:
```bash
git add .
git commit -m "Prepare release v1.0.1"
git push origin main
```

2. **Create and push tag**:
```bash
git tag -a v1.0.1 -m "Release version 1.0.1: Add new features"
git push origin v1.0.1
```

3. **Create GitHub Release** (optional but recommended):
   - Go to GitHub repository
   - Click "Releases" → "Create a new release"
   - Select tag `v1.0.1`
   - Add release notes
   - Click "Publish release"

GitHub Actions workflows will automatically:
- ✅ Build and test the library
- ✅ Create release notes with installation instructions
- ✅ Make the tag immediately consumable through JitPack

### Automated Release via GitHub UI:

1. Go to repository → Releases
2. Click "Create a new release"
3. Click "Choose a tag" → enter `v1.0.1` (create new if needed)
4. Add title and release notes
5. Click "Publish release"

The workflows will handle the rest!

---

## 📋 Pre-Release Checklist

Before creating a release:

- [ ] All tests pass: `./gradlew test`
- [ ] Library builds: `./gradlew :flexiblegrid:assembleRelease`
- [ ] Demo app builds: `./gradlew :app:assembleDebug`
- [ ] Update CHANGELOG.md (if exists)
- [ ] Verify README is up-to-date

---

## 🔗 Links

- **JitPack**: https://jitpack.io
- **Maven Central**: https://central.sonatype.com

## 📚 Additional Resources

- [Gradle Publishing Plugin Documentation](https://docs.gradle.org/current/userguide/publishing_maven.html)
- [JitPack Documentation](https://jitpack.io/docs/)
